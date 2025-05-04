package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.seatSelect.SeatIndexData
import woowacourse.movie.feature.seatSelect.SeatSelectContract
import woowacourse.movie.feature.seatSelect.SeatSelectErrorType
import woowacourse.movie.feature.seatSelect.SeatSelectPresenter
import woowacourse.movie.feature.seatSelect.SeatsData
import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatCol
import woowacourse.movie.model.ticket.seat.SeatRow

class SelectSeatPresenterTest {
    private lateinit var seatSelectPresenter: SeatSelectContract.Presenter
    private lateinit var selectSeatView: SeatSelectContract.View
    private lateinit var ticketData: TicketData
    private lateinit var screeningData: ScreeningData
    private lateinit var screening: ScreeningData

    // 테스트용 좌석 객체
    private val seatA1 = Seat(SeatRow(0), SeatCol(0))
    private val seatA2 = Seat(SeatRow(0), SeatCol(1))
    private val seatA3 = Seat(SeatRow(0), SeatCol(2))

    private val seatA1IndexData = SeatIndexData(0, 0)
    private val seatA2IndexData = SeatIndexData(0, 1)

    @BeforeEach
    fun setUp() {
        screeningData = mockk(relaxed = true)
        screening = mockk(relaxed = true)
        ticketData = mockk(relaxed = true)
        selectSeatView = mockk(relaxed = true)

        every { ticketData.screeningData.title } returns "해리포터"
        every { ticketData.totalTicketPrice } returns 20000
        every { ticketData.ticketCount } returns 2
        every { ticketData.seatsData } returns SeatsData(listOf(seatA1IndexData, seatA2IndexData))

        seatSelectPresenter = SeatSelectPresenter(selectSeatView, ticketData)
    }

    @Test
    fun `좌석 선택 UI를 초기화한다`() {
        // When
        seatSelectPresenter.initSelectSeatView()

        // Then
        verify { selectSeatView.setMovieTitle("해리포터") }
        verify { selectSeatView.setTicketPrice(any()) }
    }

    @Test
    fun `좌석 선택 시 UI가 업데이트된다`() {
        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.seatSelect(seatA1) }
        verify { selectSeatView.setTicketPrice(any()) }
        verify { selectSeatView.setSubmitButtonView(any()) }
    }

    @Test
    fun `이미 선택된 좌석을 다시 클릭하면 선택이 취소된다`() {
        // Given
        seatSelectPresenter.onSeatInput(seatA1)

        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.seatUnSelect(seatA1) }
    }

    @Test
    fun `최대 인원을 초과하여 좌석을 선택하면 오류 메시지가 표시된다`() {
        // Given
        every { ticketData.ticketCount } returns 2
        seatSelectPresenter.onSeatInput(seatA1)
        seatSelectPresenter.onSeatInput(seatA2)

        // When
        seatSelectPresenter.onSeatInput(seatA3)

        // Then
        verify { selectSeatView.printError(SeatSelectErrorType.OverBooking) }
    }

    @Test
    fun `좌석 선택 완료시 티켓 화면으로 이동한다`() {
        // Given
        seatSelectPresenter.onSeatInput(seatA1)
        seatSelectPresenter.onSeatInput(seatA2)

        // When
        seatSelectPresenter.handleCompleteSelectSeat()

        // then
        verify { selectSeatView.navigateToTicketView(any()) }
    }

    @Test
    fun `좌석 선택 상태가 변경될 때마다 제출 버튼 상태가 업데이트된다`() {
        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.setSubmitButtonView(any()) }

        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify(exactly = 2) { selectSeatView.setSubmitButtonView(any()) }
    }
}
