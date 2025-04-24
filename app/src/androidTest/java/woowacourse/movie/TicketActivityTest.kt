package woowacourse.movie

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.ticket.TicketActivity
import java.time.LocalDateTime

class TicketActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<TicketActivity> =
        ActivityScenarioRule(
            TicketActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                title = "해리 포터와 마법사의 돌",
                count = 2,
                showtime = LocalDateTime.of(2025, 4, 15, 12, 0),
            ),
        )

    @Test
    fun `영화_제목이_표시된다`() {
        onView(withId(R.id.tv_ticket_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `상영_시각이_표시된다`() {
        onView(withId(R.id.tv_ticket_showtime))
            .check(matches(withText("2025.4.15 12:00")))
    }

    @Test
    fun `인원_수가_표시된다`() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("일반 2명")))
    }

    @Test
    fun `가격이_표시된다`() {
        onView(withId(R.id.tv_ticket_price))
            .check(matches(withText("26,000원 (현장 결제)")))
    }
}
