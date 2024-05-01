package woowacourse.movie
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
import woowacourse.movie.ui.reservation.ReservationActivity

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationActivity::class.java,
        ).putExtra("screenId", 1L)

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun count_remains_when_display_turned() {
        val activityScenario = activityRule.scenario
        onView(withId(R.id.reservation_add_button)).perform(click())

        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))
    }

    @Test
    fun show_title_when_activity_starts() {
        onView(withId(R.id.reservation_title_textview))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun show_running_time_when_activity_starts() {
        onView(withId(R.id.reservation_running_time_textview))
            .check(matches(withText("152")))
    }

    @Test
    fun show_description_when_activity_starts() {
        onView(withId(R.id.reservation_description))
            .check(matches(withText("해리포터와 마법사의 돌 영화에 대한 설명입니다")))
    }

    @Test
    fun default_count_is_one() {
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun count_increase_when_add_button_clicked() {
        onView(withId(R.id.reservation_add_button))
            .perform(click())

        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))
    }

    @Test
    fun count_decrease_when_sub_button_clicked() {
        // given
        onView(withId(R.id.reservation_add_button))
            .perform(click())
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("2")))

        // when
        onView(withId(R.id.reservation_sub_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun count_does_not_decrease_when_count_less_than_two() {
        // when
        onView(withId(R.id.reservation_sub_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun layout_disappear_when_reservation_complete_button_clicked() {
        onView(withId(R.id.reservation_complete_button))
            .perform(click())

        onView(withId(R.id.reservation_layout))
            .check(doesNotExist())
    }
}