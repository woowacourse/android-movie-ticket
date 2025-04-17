package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MainActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>
    private val movie = mockMovie()

    @Before
    fun setUp() {
        Intents.init()

        val intent =
            Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
                putExtra("movieData", movie)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun `리스트뷰가_화면에_보인다`() {
        onView(withId(R.id.listview_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `리스트뷰_영화포스터가_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.img_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `리스트뷰_영화이름이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_title))
            .check(
                matches(
                    allOf(
                        withText("---"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영일이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_screening_date))
            .check(
                matches(
                    allOf(
                        withText("2025.4.1 ~ 2025.4.25"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영시간이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_running_time))
            .check(
                matches(
                    allOf(
                        withText("150분"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화예매_클릭후_데이터를_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.btn_reserve))
            .perform(click())

        intended(
            allOf(
                hasComponent(BookingActivity::class.java.name),
                hasExtra("movieData", movie),
            ),
        )
    }

    @Test
    fun `예매버튼_클릭시_BookingActivity로_이동한다`() {
        onView(withId(R.id.btn_reserve)).perform(click())

        intended(
            allOf(
                hasComponent(BookingActivity::class.java.name),
                hasExtraWithKey("movieData"),
            ),
        )
    }

    @Test
    fun `메인에서_예매버튼_클릭시_BookingActivity_로_이동하고_정보가_표시된다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.btn_reserve))
            .perform(click())

        intended(hasComponent(BookingActivity::class.java.name))

        onView(withId(R.id.tv_booking_title))
            .check(
                matches(
                    allOf(
                        withText("---"),
                        isDisplayed(),
                    ),
                ),
            )

        onView(withId(R.id.tv_booking_running_time))
            .check(matches(allOf(withText("150분"), isDisplayed())))

        onView(withId(R.id.tv_booking_screening_date))
            .check(matches(allOf(withText("2025.4.1 ~ 2025.4.25"), isDisplayed())))

        onView(withId(R.id.img_booking_poster)).check(matches(isDisplayed()))
    }

    private fun mockMovie(): Movie {
        return Movie(
            imageSource = R.drawable.harry_potter,
            title = "---",
            runningTime = 150,
            screeningStartDate = LocalDate.of(2025, 4, 1),
            screeningEndDate = LocalDate.of(2025, 4, 25),
        )
    }
}
