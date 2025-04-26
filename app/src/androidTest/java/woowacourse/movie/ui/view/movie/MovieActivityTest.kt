package woowacourse.movie.ui.view.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.view.booking.BookingActivity

class MovieActivityTest {
    private val scenario = ActivityScenario.launch(MovieActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
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
                        withText("어바웃 타임"),
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
                        withText("2025.4.21 ~ 2025.5.25"),
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
                        withText("120분"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화예매_클릭_후_이동한다`() {
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
                        withText("해리 포터와 마법사의 돌"),
                        isDisplayed(),
                    ),
                ),
            )

        onView(withId(R.id.tv_booking_running_time))
            .check(matches(allOf(withText("120분"), isDisplayed())))
        onView(withId(R.id.tv_booking_screening_date))
            .check(matches(allOf(withText("2025.4.21 ~ 2025.5.25"), isDisplayed())))
        onView(withId(R.id.img_booking_poster)).check(matches(isDisplayed()))
    }
}
