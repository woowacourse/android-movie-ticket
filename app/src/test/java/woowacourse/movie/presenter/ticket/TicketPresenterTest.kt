package woowacourse.movie.presenter.ticket

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.FakeCancelTimePolicy
import woowacourse.movie.domain.ticket.Ticket
import java.time.LocalDateTime

class TicketPresenterTest {
    private lateinit var view: TicketContract.View
    private lateinit var presenter: TicketContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            TicketPresenter(
                view,
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                setOf(Seat(1, 1), Seat(2, 2)),
                FakeCancelTimePolicy(15),
            )
    }

    @Test
    fun `티켓 정보를 전달한다`() {
        // given
        every {
            view.setCancelDescription(15)
            view.setTicket(
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                setOf(Seat(1, 1), Seat(2, 2)),
            )
        } just Runs

        // when
        presenter.fetchTicket()

        // then
        verify {
            view.setCancelDescription(15)
            view.setTicket(
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                setOf(Seat(1, 1), Seat(2, 2)),
            )
        }
    }
}
