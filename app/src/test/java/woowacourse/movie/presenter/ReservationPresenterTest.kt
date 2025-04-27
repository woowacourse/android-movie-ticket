package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.reservation.ReservationView
import java.time.LocalDate
import java.time.LocalTime

class ReservationPresenterTest {
    private lateinit var reservationPresenter: ReservationPresenter
    private lateinit var reservationView: ReservationView
    private lateinit var screeningData: ScreeningData
    private lateinit var screening: Screening
    private val startDate: LocalDate = LocalDate.of(2025, 4, 1)
    private val endDate = LocalDate.of(2025, 4, 10)

    @BeforeEach
    fun setUp() {
        screeningData = mockk<ScreeningData>()
        screening = mockk<Screening>()
        reservationView = mockk(relaxed = true)

        every { screening.capacityOfTheater } returns 20
        every { screeningData.toScreening() } returns screening
        every { reservationView.getScreeningData() } returns screeningData
        every { screening.period.start } returns startDate
        every { screening.period.endInclusive } returns endDate

        reservationPresenter = ReservationPresenter(reservationView)
    }

    @Test
    fun `티켓 수량과 시간 위치로 예약 데이터를 초기화한다`() {
        // When
        reservationPresenter.initReservationData(3, 2)

        // Then
        assertEquals(3, reservationPresenter.ticketCount.value)
        assertEquals(2, reservationPresenter.timeItemPosition)
    }

    @Test
    fun `예약 UI를 초기화한다`() {
        // When
        reservationPresenter.initReservationUI()

        // Then
        verify { reservationView.initScreeningInfoUI(screeningData) }
        verify { reservationView.setDateSelectUi(screening) }
        verify { reservationView.setTimeSelectUi(startDate, screening, 0) }
        verify { reservationView.setTicketCounterUi(reservationPresenter.ticketCount) }
    }

    @Test
    fun `날짜 변경 시 시간 선택 UI를 업데이트한다`() {
        // Given
        val selectedDate = LocalDate.of(2025, 4, 5)

        // When
        reservationPresenter.onChangedDate(selectedDate)

        // Then
        verify { reservationView.setTimeSelectUi(selectedDate, screening, 0) }
    }

    @Test
    fun `티켓 수량 증가 시 UI를 업데이트한다`() {
        // Given
        val initialCount = reservationPresenter.ticketCount.value

        // When
        reservationPresenter.increaseTicketCount()

        // Then
        assertEquals(initialCount + 1, reservationPresenter.ticketCount.value)
        verify { reservationView.setTicketCounterUi(reservationPresenter.ticketCount) }
    }

    @Test
    fun `티켓 수량 감소 시 UI를 업데이트한다`() {
        // Given
        reservationPresenter.increaseTicketCount()
        val initialCount = reservationPresenter.ticketCount.value

        // When
        reservationPresenter.decreaseTicketCount()

        // Then
        assertEquals(initialCount - 1, reservationPresenter.ticketCount.value)
        verify { reservationView.setTicketCounterUi(reservationPresenter.ticketCount) }
    }

    @Test
    fun `날짜와 시간이 선택된 경우 티켓 UI로 이동한다`() {
        // Given
        val selectedDate = LocalDate.of(2025, 4, 5)
        val selectedTime = LocalTime.of(14, 0)
        reservationPresenter.onChangedDate(selectedDate)
        reservationPresenter.onChangedTime(selectedTime)

        // When
        reservationPresenter.navigateToSelectSeatUI()

        // Then
        verify { reservationView.navigateToSelectSeatUI(any()) }
    }

    @Test
    fun `날짜나 시간이 선택되지 않은 경우 오류 메시지를 표시한다`() {
        // When
        reservationPresenter.navigateToSelectSeatUI()

        // Then
        verify { reservationView.printError("예매 정보가 선택되지 않았습니다") }
        verify(exactly = 0) { reservationView.navigateToSelectSeatUI(any()) }
    }
}
