package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.ticketingResult.TicketingResultActivity

class TicketingResultActivityTest {
    private val movieTicket = Ticket("test", "2024-04-30", listOf())

    @get:Rule
    val activityScenario: ActivityScenarioRule<TicketingResultActivity> =
        ActivityScenarioRule<TicketingResultActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                TicketingResultActivity::class.java,
            ).apply
                {
                    putExtra(TicketingResultActivity.EXTRA_MOVIE_TICKET, movieTicket)
                },
        )

    @Test
    fun `예매_결과_데이터를_보여준다`() {
        onView(withId(R.id.tv_guide)).check(matches(withText(R.string.text_guide)))
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_ticket_count)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_price)).check(matches(isDisplayed()))
    }
}
