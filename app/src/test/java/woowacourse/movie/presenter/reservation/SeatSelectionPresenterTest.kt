package woowacourse.movie.presenter.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.reservation.SeatSelectionContract
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.Ticket
import java.time.LocalDateTime

class SeatSelectionPresenterTest {
    private lateinit var view: SeatSelectionContract.View
    private lateinit var presenter: SeatSelectionContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            SeatSelectionPresenter(
                view,
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                selectedSeats = emptySet(),
            )
    }

    @Test
    fun `좌석들을 보여줄 수 있다`() {
        // given
        every { view.setSeats(Seat.Companion.seats(), emptySet()) } just Runs

        // when
        presenter.presentSeats()

        // then
        verify { view.setSeats(Seat.Companion.seats(), emptySet()) }
    }

    @Test
    fun `영화 제목을 보여줄 수 있다`() {
        // given
        every { view.setTitle("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.presentTitle()

        // then
        verify { view.setTitle("해리 포터와 마법사의 돌") }
    }

    @Test
    fun `가격을 보여줄 수 있다`() {
        // given
        presenter =
            SeatSelectionPresenter(
                view,
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                selectedSeats = setOf(Seat.Companion(1, 1)),
            )
        every { view.setPrice(10000) } just Runs

        // when
        presenter.presentPrice()

        // then
        verify { view.setPrice(10000) }
    }

    @Test
    fun `좌석을 선택할 수 있다`() {
        // given
        every { view.setSeatIsSelected(Seat.Companion(1, 1), true) } just Runs
        every { view.setPrice(10000) } just Runs

        // when
        presenter.onSeatSelect(Seat.Companion(1, 1))

        // then
        verify { view.setSeatIsSelected(Seat.Companion(1, 1), true) }
    }

    @Test
    fun `선택된 좌석을 해제할 수 있다`() {
        // given
        presenter =
            SeatSelectionPresenter(
                view,
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                selectedSeats = setOf(Seat.Companion(1, 1)),
            )

        every { view.setSeatIsSelected(Seat.Companion(1, 1), false) } just Runs
        every { view.setPrice(0) } just Runs

        // when
        presenter.onSeatSelect(Seat.Companion(1, 1))

        // then
        verify { view.setSeatIsSelected(Seat.Companion(1, 1), false) }
    }

    @Test
    fun `좌석을 선택하면 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다`() {
        // given
        every { view.setSeatIsSelected(Seat.Companion(1, 1), true) } just Runs
        every { view.setPrice(10_000) } just Runs

        // when
        presenter.onSeatSelect(Seat.Companion(1, 1))

        // then
        verify { view.setPrice(10_000) }
    }

    @Test
    fun `예매 확인 다이얼로그를 띄울 수 있다`() {
        // given
        every { view.askFinalReservation() } just Runs

        // when
        presenter.tryReservation()

        // then
        verify { view.askFinalReservation() }
    }

    @Test
    fun `예매를 완료할 수 있다`() {
        // given
        every {
            view.navigateToTicketScreen(
                "해리 포터와 마법사의 돌",
                2,
                LocalDateTime.of(2025, 4, 15, 11, 0),
            )
        } just Runs

        // when
        presenter.confirmReservation()

        // then
        verify {
            view.navigateToTicketScreen(
                "해리 포터와 마법사의 돌",
                2,
                LocalDateTime.of(2025, 4, 15, 11, 0),
            )
        }
    }
}
