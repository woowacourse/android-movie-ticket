package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.presentation.detail.TicketDetailActivity
import woowacourse.movie.presentation.reservation.model.toTicketModel

@RunWith(AndroidJUnit4::class)
class TicketCounterCompleteTest {
    private val ticket =
        Ticket(
            "해리 포터와 마법사의 돌",
            "2024.3.1",
            2,
        ).toTicketModel()
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            TicketDetailActivity::class.java,
        ).also { it.putExtra("ticket", ticket) }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<TicketDetailActivity>(intent)

    @Test
    fun `티켓의_총_금액이_표시되어야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText("26,000원 (현장결제)")))
    }

    @Test
    fun `티켓의_총_인원이_표시되어야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.ticket_number_of_people))
            .check(ViewAssertions.matches(ViewMatchers.withText("일반 2명")))
    }
}
