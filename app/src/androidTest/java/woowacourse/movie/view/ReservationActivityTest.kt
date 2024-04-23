package woowacourse.movie.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.presentation.reservation.booking.ReservationActivity

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val movie = MovieDao().find(0)
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationActivity::class.java,
        ).putExtra("img", movie.img)
            .putExtra("title", movie.title)
            .putExtra("description", movie.description)
            .putExtra("screenDate", movie.screenDateToString())
            .putExtra("runningTime", movie.runningTime.toString())

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun 액티비티가_시작하면_title이_보인다() {
        onView(withId(R.id.reservation_title_textview))
            .check(matches(withText("해리포터와 마법사의 돌")))
    }

    @Test
    fun 액티비티가_시작하면_running_time이_보인다() {
        onView(withId(R.id.reservation_running_time_textview))
            .check(matches(withText("152")))
    }

    @Test
    fun 액티비티가_시작하면_description이_보인다() {
        onView(withId(R.id.reservation_description))
            .check(matches(withText(movie.description)))
    }

    @Test
    fun count의_기본값은_1이다() {
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun add_button을_클릭하면_count의_값이_1증가한다() {
        onView(withId(R.id.add_button))
            .perform(click())

        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))
    }

    @Test
    fun sub_button을_클릭하면_count의_값이_1감소한다() {
        // given
        onView(withId(R.id.add_button))
            .perform(click())
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))

        // when
        onView(withId(R.id.sub_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun count의_값이_2보다_작을때_sub_button을_클릭해도_count는_변하지않는다() {
        // when
        onView(withId(R.id.sub_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun 예매완료_버튼을_누르면_현재_레이아웃은_사라져야한다() {
        onView(withId(R.id.reservation_complete_button))
            .perform(click())

        onView(withId(R.id.reservation_layout))
            .check(doesNotExist())
    }

    @Test
    fun 티켓의_수를_2로_증가시킨_후_화면_회전시_유지된다() {
        // given
        onView(withId(R.id.add_button))
            .perform(click())

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))
    }
}
