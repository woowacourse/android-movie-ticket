package woowacourse.movie.presentation.reservation.result

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.cinema.seat.Seat
import woowacourse.movie.domain.model.cinema.seat.SeatType
import woowacourse.movie.domain.model.cinema.ticket.Ticket
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import woowacourse.movie.presentation.model.toUiModel
import woowacourse.movie.presentation.view.reservation.result.ReservationResultContract
import woowacourse.movie.presentation.view.reservation.result.ReservationResultPresenter
import java.time.LocalDateTime

class ReservationResultPresenterTest {
    private lateinit var view: ReservationResultContract.View
    private lateinit var presenter: ReservationResultContract.Presenter
    private val ticketBundle =
        TicketBundle(
            listOf(
                Ticket(
                    "해리포터",
                    LocalDateTime.of(2025, 4, 1, 0, 0),
                    seat = Seat(1, 1, SeatType.B_CLASS),
                    10_000,
                ),
            ),
        ).toUiModel()

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationResultPresenter(view)
    }

    @Test
    fun `티켓 정보를 불러온다`() {
        // Given: View가 setScreen 동작을 설정한다
        every { view.setScreen(any(), any()) } just Runs

        // When: presenter가 데이터를 불러온다
        presenter.fetchDate(ticketBundle)

        // Then: View의 setScreen이 티켓 번들의 정보와 함께 호출된다.
        verify {
            view.setScreen(
                withArg {
                    assert(it.title == ticketBundle.title)
                    assert(it.labels == ticketBundle.labels)
                    assert(it.size == ticketBundle.size)
                },
                any(),
            )
        }
    }

    @Test
    fun `예매 정보를 불러오지 못한 경우 다이얼로그를 보여준다`() {
        // Given: View가 notifyInvalidReservationInfo 동작을 설정한다
        every { view.notifyInvalidReservationInfo() } just Runs

        // When: presenter가 데이터를 불러온다
        presenter.fetchDate(null)

        // Then: View가 예매 정보가 유효하지 않음을 알리는 다이얼로그를 보여준다.
        verify { view.notifyInvalidReservationInfo() }
    }
}
