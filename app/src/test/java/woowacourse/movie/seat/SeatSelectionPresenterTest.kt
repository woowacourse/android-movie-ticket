package woowacourse.movie.seat

import android.widget.TextView
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import java.time.LocalDate
import java.time.LocalTime

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var mockView: SeatSelectionContract.View
    private lateinit var mockTicket: Ticket
    private lateinit var mockTicketUiData: TicketUiModel

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)

        mockTicket =
            Ticket(
                title = "해리 포터와 마법사의 돌",
                headCount = HeadCount(3),
                selectedDate = LocalDate.of(2028, 10, 13),
                selectedTime = LocalTime.of(11, 0),
                seats = Seats(emptyList()),
            )

        mockTicketUiData = mockTicket.toUiModel()

        presenter = SeatSelectionPresenter(view = mockView, ticket = mockTicketUiData)
    }

    @Test
    fun `티켓데이터가_null이면_오류_메시지를_띄우고_종료한다`() {
        presenter = SeatSelectionPresenter(view = mockView, ticket = null)

        presenter.initializeData(null)

        verify { mockView.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.") }
        confirmVerified(mockView)
    }

    @Test
    fun `티켓을_바탕으로_View에_초기_데이터를_보여준다`() {
        presenter = SeatSelectionPresenter(view = mockView, ticket = mockTicketUiData)

        presenter.initializeData(null)

        verify { mockView.showTicket(mockTicketUiData) }
    }

    @Test
    fun `좌석을_클릭하면_좌석_상태와_버튼_활성화_상태를_갱신한다`() {
        val seatTextView = mockk<TextView>(relaxed = true)
        every { seatTextView.text.toString() } returns "A1"

        presenter.initializeData(null)
        presenter.onSeatClicked(seatTextView)

        verify { mockView.showSeatState(seatTextView, isSelected = true) }
        verify { mockView.setButtonEnabled(any()) }
        verify { mockView.showTicket(match { it.seats == "A1" && it.totalPrice == "10,000" }) }
    }

    @Test
    fun `예약버튼_클릭시_예약_다이얼로그를_표시한다`() {
        presenter.initializeData(null)

        presenter.onButtonClicked()

        verify { mockView.showBookingAlertDialog(any()) }
    }
}
