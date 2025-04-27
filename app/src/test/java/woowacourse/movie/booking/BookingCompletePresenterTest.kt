package woowacourse.movie.booking

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.complete.BookingCompleteContract
import woowacourse.movie.booking.complete.BookingCompletePresenter
import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import java.time.LocalDate
import java.time.LocalTime

class BookingCompletePresenterTest {
    private lateinit var presenter: BookingCompletePresenter
    private lateinit var mockView: BookingCompleteContract.View
    private lateinit var mockTicket: Ticket
    private lateinit var mockTicketUiData: TicketUiModel

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)

        val seats =
            listOf(
                Seat("A1", true),
                Seat("C2", true),
                Seat("E1", true),
            )

        mockTicket =
            Ticket(
                title = "해리 포터와 마법사의 돌",
                headCount = HeadCount(3),
                selectedDate = LocalDate.of(2028, 10, 13),
                selectedTime = LocalTime.of(11, 0),
                seats = Seats(seats),
            )

        mockTicketUiData = mockTicket.toUiModel()

        presenter = BookingCompletePresenter(view = mockView, ticket = mockTicketUiData)
    }

    @Test
    fun `영화 예매 정보가 null이면 오류 메시지를 띄우고 종료한다`() {
        presenter = BookingCompletePresenter(view = mockView, ticket = null)

        presenter.initializeData(null)

        verify { mockView.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.") }
        confirmVerified(mockView)
    }

    @Test
    fun `영화 예매 정보를 화면에 표시할 수 있다`() {
        presenter = BookingCompletePresenter(view = mockView, ticket = mockTicketUiData)

        presenter.initializeData(null)

        verify { mockView.showBookingCompleteResult(mockTicketUiData) }
        verify {
            mockView.showBookingCompleteResult(
                match {
                    it.headCount == 3 && it.selectedDateText == "2028.10.13" &&
                        it.selectedTimeText == "11:00" && it.seats == "A1,C2,E1" &&
                        it.totalPrice == "37,000"
                },
            )
        }
    }
}
