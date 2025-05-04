package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.seatSelect.SeatSelectPresenter
import woowacourse.movie.feature.seatSelect.SeatsData
import woowacourse.movie.feature.seatSelect.SelectSeatView
import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatCol
import woowacourse.movie.model.ticket.seat.SeatRow

class SelectSeatPresenterTest {
    private lateinit var seatSelectPresenter: SeatSelectPresenter
    private lateinit var selectSeatView: SelectSeatView
    private lateinit var ticketData: TicketData
    private lateinit var screeningData: ScreeningData
    private lateinit var screening: ScreeningData

    // 테스트용 좌석 객체
    private val seatA1 = Seat(SeatRow(0), SeatCol(0))
    private val seatA2 = Seat(SeatRow(0), SeatCol(1))
    private val seatA3 = Seat(SeatRow(0), SeatCol(2))

    @BeforeEach
    fun setUp() {
        screeningData = mockk(relaxed = true)
        screening = mockk(relaxed = true)
        ticketData = mockk(relaxed = true)
        selectSeatView = mockk(relaxed = true)

        every { ticketData.screeningData } returns screeningData
        every { ticketData.ticketCount } returns 2
        every { screeningData.toScreening() } returns screening
        every { selectSeatView.getTicketData() } returns ticketData
        every { ticketData.seatSelectedTicketData(any()) } returns ticketData

        seatSelectPresenter = SeatSelectPresenter(selectSeatView, getTicketData())
    }

    @Test
    fun `좌석 선택 UI를 초기화한다`() {
        // When
        seatSelectPresenter.initSelectSeatView()

        // Then
        verify { selectSeatView.initMovieTitleUI(ticketData) }
        verify { selectSeatView.setTicketPrice(any()) }
    }

    @Test
    fun `좌석 선택 시 UI가 업데이트된다`() {
        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.seatSelect(seatA1) }
        verify { selectSeatView.setTicketPrice(any()) }
        verify { selectSeatView.updateSubmitButton() }
    }

    @Test
    fun `이미 선택된 좌석을 다시 클릭하면 선택이 취소된다`() {
        // Given
        seatSelectPresenter.onSeatInput(seatA1)

        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.seatUnSelect(seatA1) }
        assertFalse(seatSelectPresenter.getSeatsData.isSelectedSeat(seatA1))
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
        verify { selectSeatView.printError("관람 인원을 초과하여\n좌석을 선택할 수 없습니다") }
    }

    @Test
    fun `최대 인원 선택 여부를 정확히 판단한다`() {
        // Given
        every { ticketData.ticketCount } returns 2

        // WhenThen
        assertFalse(seatSelectPresenter.isMaximumSelectedSeat())

        seatSelectPresenter.onSeatInput(seatA1)
        assertFalse(seatSelectPresenter.isMaximumSelectedSeat())

        seatSelectPresenter.onSeatInput(seatA2)
        assertTrue(seatSelectPresenter.isMaximumSelectedSeat())
    }

    @Test
    fun `좌석 토글 시 선택 상태에 따라 적절한 UI가 업데이트된다`() {
        // When - 좌석 선택
        seatSelectPresenter.toggleSeat(seatA1)

        // Then
        verify { selectSeatView.seatSelect(seatA1) }
        verify { selectSeatView.setTicketPrice(any()) }

        // When - 좌석 선택 취소
        seatSelectPresenter.toggleSeat(seatA1)

        // Then
        verify { selectSeatView.seatUnSelect(seatA1) }
        verify(exactly = 2) { selectSeatView.setTicketPrice(any()) }
    }

    @Test
    fun `티켓 화면으로 이동 시 선택된 좌석 정보가 전달된다`() {
        // Given
        seatSelectPresenter.toggleSeat(seatA1)
        seatSelectPresenter.toggleSeat(seatA2)

        // When
        seatSelectPresenter.handleCompleteSelectSeat()

        // Then
        verify {
            ticketData.seatSelectedTicketData(
                match<SeatsData> { seatsData ->
                    seatsData.seatsLength == 2 &&
                        seatsData.seatsCodes.size == 2 &&
                        seatsData.seatsCodes.contains(seatA1.seatCode) &&
                        seatsData.seatsCodes.contains(seatA2.seatCode)
                },
            )
        }
        verify { selectSeatView.navigateToTicketUI(ticketData) }
    }

    @Test
    fun `선택된 좌석이 없을 때 총 가격은 0원이다`() {
        // When
        val price = seatSelectPresenter.getSeatsData.totalTicketPrice

        // Then
        assertEquals(0, price.value)
    }

    @Test
    fun `좌석 선택 상태가 변경될 때마다 제출 버튼 상태가 업데이트된다`() {
        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify { selectSeatView.updateSubmitButton() }

        // When
        seatSelectPresenter.onSeatInput(seatA1)

        // Then
        verify(exactly = 2) { selectSeatView.updateSubmitButton() }
    }
}
