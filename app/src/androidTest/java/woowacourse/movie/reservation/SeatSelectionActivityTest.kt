package woowacourse.movie.reservation

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
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
                3,
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
}
