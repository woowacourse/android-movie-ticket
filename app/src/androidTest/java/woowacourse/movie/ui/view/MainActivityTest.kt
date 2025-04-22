package woowacourse.movie.ui.view

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
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.view.booking.BookingActivity
import woowacourse.movie.util.Keys
import java.time.LocalDate

class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val movies = mockMovies()

    @Before
    fun setUp() {
        Intents.init()

        val intent =
            Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
                putExtra(Keys.Extra.LOADED_MOVIE_ITEMS, movies)
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
            .atPosition(1)
            .onChildView(withId(R.id.tv_movie_title))
            .check(
                matches(
                    allOf(
                        withText("해리포터와 불의 잔 - 테스트용"),
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
                        withText("151분"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화예매_클릭후_데이터를_확인한다`() {
        // given && when
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.btn_reserve))
            .perform(click())

        // then
        intended(
            allOf(
                hasComponent(BookingActivity::class.java.name),
                hasExtra(Keys.Extra.SELECTED_MOVIE_ITEM, movies[0]),
            ),
        )
    }

    @Test
    fun `메인에서_예매버튼_클릭시_BookingActivity_로_이동하고_정보가_표시된다`() {
        // given && when
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.btn_reserve))
            .perform(click())

        // then
        intended(hasComponent(BookingActivity::class.java.name))
        onView(withId(R.id.tv_booking_title))
            .check(
                matches(
                    allOf(
                        withText("테스트 데이터 제목"),
                        isDisplayed(),
                    ),
                ),
            )

        onView(withId(R.id.tv_booking_running_time))
            .check(matches(allOf(withText("151분"), isDisplayed())))
        onView(withId(R.id.tv_booking_screening_date))
            .check(matches(allOf(withText("2025.4.1 ~ 2025.4.25"), isDisplayed())))
        onView(withId(R.id.img_booking_poster)).check(matches(isDisplayed()))
    }

    private fun mockMovies(): ArrayList<Movie> {
        return arrayListOf(
            Movie(
                imageSource = R.drawable.harry_potter,
                title = "테스트 데이터 제목",
                runningTime = 151,
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
            ),
            Movie(
                imageSource = R.drawable.harry_potter,
                title = "해리포터와 불의 잔 - 테스트용",
                runningTime = 150,
                screeningStartDate = LocalDate.of(2025, 4, 12),
                screeningEndDate = LocalDate.of(2025, 4, 25),
            ),
        )
    }
}
