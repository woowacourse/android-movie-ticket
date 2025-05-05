package woowacourse.movie.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movieseat.Position
import woowacourse.movie.view.reservation.seat.ReservationSeatContract
import woowacourse.movie.view.reservation.seat.ReservationSeatPresenter
import java.time.LocalDateTime

class ReservationSeatPresenterTest {
    private lateinit var view: ReservationSeatContract.View
    private lateinit var presenter: ReservationSeatPresenter
    private lateinit var ticket: Ticket

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = ReservationSeatPresenter(view)
        ticket =
            Ticket(
                "해리 포터와 마법사의 돌",
                LocalDateTime.of(2025, 4, 21, 18, 0),
                2,
            )
    }

    @Test
    fun `fetchData 호출 시 view 초기화 메서드들이 호출된다`() {
        // given
        val moneySlot = slot<Int>()
        val movieNameSlot = slot<String>()

        every { view.setSeatTag() } just Runs
        every { view.setSeatInit() } just Runs
        every { view.showMovieName(capture(movieNameSlot)) } just Runs
        every { view.setSeatClickListener() } just Runs
        every { view.setReservationButton(any()) } just Runs
        every { view.showTicketMoney(capture(moneySlot)) } just Runs

        // when
        presenter.fetchData(ticket)
        // then
        verify { view.setSeatTag() }
        verify { view.setSeatInit() }
        verify { view.showMovieName(capture(movieNameSlot)) }
        verify { view.setSeatClickListener() }
        verify { view.setReservationButton(any()) }
        verify { view.showTicketMoney(capture(moneySlot)) }

        assertThat(movieNameSlot.captured).isEqualTo("해리 포터와 마법사의 돌")
        assertThat(moneySlot.captured).isEqualTo(0)
    }

    @Test
    fun `좌석 선택 시 seat가 추가되고 view가 갱신된다`() {
        // given
        val positionSlot = slot<Position>()
        val moneySlots = mutableListOf<Int>()
        val position = Position(1, 1)

        every { view.setSeatTag() } just Runs
        every { view.setSeatInit() } just Runs
        every { view.showMovieName(any()) } just Runs
        every { view.setSeatClickListener() } just Runs
        every { view.setReservationButton(any()) } just Runs
        every { view.showTicketMoney(capture(moneySlots)) } just Runs
        every { view.selectSeatView(capture(positionSlot)) } just Runs
        every { view.deSelectableButton() } just Runs

        // when
        presenter.fetchData(ticket)
        presenter.selectSeat(position)

        // then
        verify { view.selectSeatView(capture(positionSlot)) }
        verify(exactly = 2) { view.showTicketMoney(any()) }
        verify { view.deSelectableButton() }

        assertThat(positionSlot.captured).isEqualTo(position)
        assertThat(moneySlots).containsExactly(0, 10000)
    }

    @Test
    fun `좌석 선택 제한을 초과하면 추가되지 않는다`() {
        // given
        every { view.setSeatTag() } just Runs
        every { view.setSeatInit() } just Runs
        every { view.showMovieName(any()) } just Runs
        every { view.setSeatClickListener() } just Runs
        every { view.setReservationButton(any()) } just Runs
        every { view.showTicketMoney(any()) } just Runs
        every { view.selectSeatView(any()) } just Runs
        every { view.selectableButton() } just Runs
        every { view.deSelectableButton() } just Runs

        // when
        presenter.fetchData(ticket)

        presenter.selectSeat(Position(1, 1))
        presenter.selectSeat(Position(1, 2))
        presenter.selectSeat(Position(1, 3))
        // then
        verify(exactly = 2) { view.selectSeatView(any()) }
    }

    @Test
    fun `좌석 해제 시 seat가 제거되고 view가 갱신된다`() {
        val moneySlots = mutableListOf<Int>()
        val position = Position(2, 3)

        every { view.setSeatTag() } just Runs
        every { view.setSeatInit() } just Runs
        every { view.showMovieName(any()) } just Runs
        every { view.setSeatClickListener() } just Runs
        every { view.setReservationButton(any()) } just Runs
        every { view.showTicketMoney(capture(moneySlots)) } just Runs
        every { view.selectSeatView(position) } just Runs
        every { view.deselectSeatView(position) } just Runs
        every { view.selectableButton() } just Runs
        every { view.deSelectableButton() } just Runs

        // when
        presenter.fetchData(ticket)
        presenter.selectSeat(position)
        presenter.deselectSeat(position)
        // then
        verify { view.deselectSeatView(position) }
        verify(exactly = 3) { view.showTicketMoney(any()) }
        assertThat(moneySlots).containsExactly(0, 15000, 0)
    }
}
