package woowacourse.movie.reservation

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.view.reservation.SeatSelectionActivity
import java.time.LocalDateTime

class SeatSelectionActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<SeatSelectionActivity>(
            SeatSelectionActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                "해리 포터와 마법사의 돌",
                1,
                LocalDateTime.of(2025, 4, 15, 11, 0),
            ),
        )

    @Test
    fun `영화_제목이_표시된다`() {
        onView(withId(R.id.tv_seat_selection_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `각_행은_알파벳_열은_숫자로_표현한다`() {
        onView(withId(R.id.layout_seat_selection_seats))
            .check(matches(withChild(withChild(withText("A1")))))
    }

    @Test
    fun `B등급은_보라색_글자로_표시한다`() {
        onView(withText("A1"))
            .check(matches(hasTextColor(R.color.seat_grade_b)))
    }

    @Test
    fun `S등급은_초록색_글자로_표시한다`() {
        onView(withText("C1"))
            .check(matches(hasTextColor(R.color.seat_grade_s)))
    }

    @Test
    fun `A등급은_파란색_글자로_표시한다`() {
        onView(withText("E1"))
            .check(matches(hasTextColor(R.color.seat_grade_a)))
    }

    @Test
    fun `예매_완료를_확인하는_다이얼로그가_표시되고_배경을_터치해도_사라지지_않아야_한다`() {
        // given
        onView(withText("D1"))
            .perform(click())
        onView(withId(R.id.btn_seat_selection_complete))
            .perform(click())

        // when
        onView(isRoot())
            .perform(click())
        // then

        onView(withText(R.string.ticket_dialog_title))
            .check(matches(isDisplayed()))
    }
}
