package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity.Companion.EXTRA_COUNT
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity.Companion.EXTRA_SCREENING_DATE_TIME

class SeatSelectionActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<SeatSelectionActivity> =
        ActivityScenarioRule<SeatSelectionActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                putExtra(EXTRA_MOVIE_ID, 0)
                putExtra(EXTRA_COUNT, 2) // 선택 가능한 좌석 개수
                putExtra(EXTRA_SCREENING_DATE_TIME, "")
            },
        )

    @Test
    fun `좌석을_선택하면_선택된_좌석의_배경색이_변한다`() {
        onView(withId(R.id.tv_seat_item_0))
            .perform(click())
            .check(matches(isSelected()))
    }

    @Test
    fun `선택된_좌석을_재선택하면_해당_좌석의_선택이_해제되며_배경색이_원래대로_변한다`() {
        onView(withId(R.id.tv_seat_item_0))
            .perform(click())
            .perform(click())
            .check(matches(not(isSelected())))
    }

    @Test
    fun `선택_가능한_좌석_개수만큼_좌석이_선택되었을_경우에는_하단_버튼이_활성화된다`() {
        onView(withId(R.id.tv_seat_item_0)).perform(click())
        onView(withId(R.id.tv_seat_item_1)).perform(click())

        onView(withId(R.id.btn_complete)).check(matches(isEnabled()))
    }

    @Test
    fun `선택_가능한_좌석_개수만큼_좌석이_선택되지_않았을_경우에는_하단_버튼이_비활성화된다`() {
        onView(withId(R.id.tv_seat_item_0)).perform(click())

        onView(withId(R.id.btn_complete)).check(matches(not(isEnabled())))
    }

    @Test
    fun `활성화된_하단_버튼을_클릭하면_예매_확인_다이얼로그_창이_뜬다`() {
        onView(withId(R.id.tv_seat_item_0)).perform(click())
        onView(withId(R.id.tv_seat_item_1)).perform(click())
        onView(withId(R.id.btn_complete)).perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun `좌석_선택_후_화면_회전_시에도_선택했던_좌석_데이터는_유지된다`() {
        onView(withId(R.id.tv_seat_item_0)).perform(click())
        onView(withId(R.id.tv_seat_item_1)).perform(click())

        val activityScenario = activityRule.scenario
        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_seat_item_0)).check(matches(isSelected()))
        onView(withId(R.id.tv_seat_item_1)).check(matches(isSelected()))
    }
}
