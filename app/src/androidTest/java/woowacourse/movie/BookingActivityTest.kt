package woowacourse.movie

import android.content.Intent
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Test
import woowacourse.movie.data.MovieInfo

class BookingActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>

    @Before
    fun setupIntent() {
        val movieInfo = MovieInfo(R.drawable.harry_potter_poster, "해리 포터와 마법사의 돌", "2025.4.1", "2025.4.25", "152분")

        val intent =
            Intent(ApplicationProvider.getApplicationContext(), BookingActivity::class.java).apply {
                putExtra("MOVIE_INFO", movieInfo)
            }

        scenario = ActivityScenario.launch<BookingActivity>(intent)
        onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
    }

    @Test
    fun 증가_버튼을_누르면_카운트가_1씩_증가한다() {
        onView(withId(R.id.plus_button))
            .perform(click())

        onView(withId(R.id.ticket_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 감소_버튼을_누르면_카운트가_1씩_감소한다() {
        scenario.onActivity { activity ->
            activity.findViewById<TextView>(R.id.ticket_count).text = "1"
        }

        scenario.use {
            onView(withId(R.id.minus_button))
                .perform(click())

            onView(withId(R.id.ticket_count))
                .check(matches(withText("0")))
        }
    }

    @Test
    fun 평일_날짜를_선택하면_10시부터_상영이_시작된다() {
        onView(withId(R.id.movie_date))
            .perform(click())

        onData(anything())
            .atPosition(16)
            .perform(click())

        onView(withId(R.id.movie_time))
            .check(matches(withSpinnerText("10:00")))
    }

    @Test
    fun 주말_날짜를_선택하면_9시부터_상영이_시작된다() {
        onView(withId(R.id.movie_date))
            .perform(click())

        onData(anything())
            .atPosition(18)
            .perform(click())

        onView(withId(R.id.movie_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 화면이_회전되어도_입력된_정보가_유지된다() {
        // given
        onView(withId(R.id.movie_date))
            .perform(click())

        onData(anything())
            .atPosition(1)
            .perform(click())

        onView(withId(R.id.movie_time))
            .perform(click())

        onData(anything())
            .atPosition(1)
            .perform(click())

        onView(withId(R.id.plus_button))
            .perform(click())

        // when
        onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)

        // then
        onView(withId(R.id.movie_date))
            .check(matches(withSpinnerText("2025.4.2")))

        onView(withId(R.id.movie_time))
            .check(matches(withSpinnerText("12:00")))

        onView(withId(R.id.ticket_count))
            .check(matches(withText("1")))
    }
}
