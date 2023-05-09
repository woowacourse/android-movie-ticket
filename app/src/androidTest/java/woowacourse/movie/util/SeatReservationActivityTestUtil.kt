package woowacourse.movie.util

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.SeatReservationActivityTest
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.seatreservation.SeatReservationActivity

private var intent =
    SeatReservationActivity.getIntent(
        ApplicationProvider.getApplicationContext(),
        Ticket(
            SeatReservationActivityTest.MOVIE,
            SeatReservationActivityTest.TIME,
            SeatReservationActivityTest.DEFAULT_TICKET_COUNT,
        ),
    )

private lateinit var scenario: ActivityScenario<SeatReservationActivity>

fun startTest(ticket: Ticket) {
    ticket.let {
        intent = SeatReservationActivity.getIntent(
            ApplicationProvider.getApplicationContext(),
            it,
        )
    }
    scenario = ActivityScenario.launch(intent)
}

fun setTicketCount(count: Int): Ticket = Ticket(
    SeatReservationActivityTest.MOVIE,
    SeatReservationActivityTest.TIME,
    count,
)
