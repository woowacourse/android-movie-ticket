package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixtures.context

class MovieReservationActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<MovieReservationActivity>(
            Intent(
                context,
                MovieReservationActivity::class.java,
            ).apply {
                putExtra(MovieReservationActivity.EXTRA_SCREEN_MOVIE_ID, 1L)
            },
        )

    @Test
    @DisplayName("Activity가 실행되면 뷰가 보인다")
    fun view_is_display_when_Activity_is_created() {
        onView(withId(R.id.detail_movie)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("플러스 버튼을 클릭하면 숫자가 증가한다.")
    fun count_increase_when_click_plus_btn() {
        onView(withId(R.id.btn_detail_plus)).perform(click())

        onView(withId(R.id.tv_detail_count)).check(matches(withText("2")))
    }

    @Test
    @DisplayName("마이너스 버튼을 클릭하면 숫자가 증가한다.")
    fun count_decrease_when_click_minus_btn() {
        onView(withId(R.id.btn_detail_plus)).perform(click())
        onView(withId(R.id.tv_detail_count)).check(matches(withText("2")))

        onView(withId(R.id.btn_detail_minus)).perform(click())
        onView(withId(R.id.tv_detail_count)).check(matches(withText("1")))
    }

    @Test
    @DisplayName("인원수를 2로 증가시킨 후 회진시 데이터가 유지된다.")
    fun data_is_maintained_when_rotate_the_screen() {
        onView(withId(R.id.btn_detail_plus)).perform(click())

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_detail_count)).check(matches(withText("2")))
    }
}
