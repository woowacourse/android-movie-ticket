package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.ui.activity.SeatPickerActivity

class SeatPickerActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SeatPickerActivity::class.java)

    @Test
    fun 사용자가_빈_좌석을_누르면_좌석이_선택된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
    }

    @Test
    fun 사용자가_이미_선택된_좌석을_누르면_좌석_선택이_해제된다() {
        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isSelected()))
            .perform(click())

        onView(withId(R.id.seat_view_1))
            .check(matches(isNotSelected()))
    }
}
