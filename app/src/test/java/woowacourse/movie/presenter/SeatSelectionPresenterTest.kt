package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatType
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.seat.SeatSelectionContract
import woowacourse.movie.presentation.seat.SeatSelectionPresenter
import java.time.LocalDate
import java.time.LocalDateTime

class SeatSelectionPresenterTest {
    private lateinit var mockView: SeatSelectionContract.View
    private lateinit var mockSeatRepository: SeatRepository
    private lateinit var presenter: SeatSelectionPresenter
    private val ticketModel =
        TicketModel(
            "해리포터",
            LocalDate.now(),
            LocalDateTime.now(),
            listOf("A1", "A2", "A3"),
            3,
            12000,
        )

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)
        mockSeatRepository = mockk(relaxed = true)
        presenter = SeatSelectionPresenter(ticketModel, mockView, mockSeatRepository)
    }

    @Test
    fun `loadTicket은 티켓 정보를 뷰에 표시해야 한다`() {
        presenter.loadTicket()
        verify { mockView.showTicket(ticketModel) }
    }

    @Test
    fun `loadSeat은 좌석 정보를 뷰에 표시해야 한다`() {
        val seats = listOf(listOf(MovieSeat("A1", SeatType.S), MovieSeat("A2", SeatType.S)))
        every { mockSeatRepository.getAvailableSeats() } returns seats

        presenter.loadSeat()

        verify { mockView.showSeat(seats) }
    }

    @Test
    fun `selectSeat은 선택된 좌석에 따라 뷰를 업데이트 해야 한다`() {
        val rowIndex = 0
        val columnIndex = 0
        val seat = MovieSeat("A1", SeatType.S)
        every { mockSeatRepository.getAvailableSeat(rowIndex, columnIndex) } returns seat

        presenter.selectSeat(rowIndex, columnIndex)

        verify {
            mockView.showSelectedSeat(rowIndex, columnIndex)
            mockView.showCurrentResultTicketPriceView(any())
        }
    }

    @Test
    fun `ticketing은 최종 티켓 정보를 이동시켜야 한다`() {
        presenter.ticketing()

        verify { mockView.moveToTicketDetail(any()) }
    }

    @Test
    fun `confirmSeatResult은 조건에 따라 대화상자를 표시해야 한다`() {
        presenter.confirmSeatResult()

        verify(exactly = 0) { mockView.showDialog() }
    }
}
