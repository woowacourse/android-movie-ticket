package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.reservation.ReservationContract
import woowacourse.movie.feature.reservation.ReservationErrorType
import woowacourse.movie.feature.reservation.ReservationPresenter
import woowacourse.movie.model.movieSelect.screening.Screening
import java.time.LocalDate
import java.time.LocalTime

class ReservationPresenterTest {
    private lateinit var reservationPresenter: ReservationContract.Presenter
    private lateinit var reservationView: ReservationContract.View
    private lateinit var screeningData: ScreeningData
    private lateinit var screening: Screening
    private val startDate: LocalDate = LocalDate.of(2025, 4, 1)
    private val endDate = LocalDate.of(2025, 4, 2)
    private val screeningDates = mutableListOf(startDate, endDate)

    @BeforeEach
    fun setUp() {
        screeningData = mockk<ScreeningData>()
        screening = mockk<Screening>()
        reservationView = mockk(relaxed = true)

        every { screening.getScreeningDates() } returns screeningDates
        every { screeningData.toScreening() } returns screening
        every { screening.title } returns "해리포터"
        every { screening.period } returns startDate..endDate
        every { screening.movieId } returns "harryPorter1"
        every { screening.runningTime } returns 120
        every { screening.capacityOfTheater } returns 20

        reservationPresenter = ReservationPresenter(reservationView, screening)
    }

    @Test
    fun `티켓 수량 데이터를 초기화한다`() {
        // When
        reservationPresenter.recoverReservationData(3)

        // Then
        assertEquals(3, reservationPresenter.getTicketCountValue())
    }

    @Test
    fun `예약 UI를 초기화한다`() {
        // When
        reservationPresenter.initReservationView()

        // Then
        verify { reservationView.setDateSelectUi(screeningDates) }
        verify { reservationView.showScreeningData(any()) }
        verify { reservationView.setTicketCounterUi(any()) }
    }

    @Test
    fun `날짜 변경 시 시간 선택 UI를 업데이트한다`() {
        // Given
        val selectedDate = LocalDate.of(2025, 4, 2)

        // When
        reservationPresenter.onChangedDate(selectedDate)

        // Then
        verify { reservationView.setTimeSelectUi(selectedDate, screening) }
    }

    @Test
    fun `티켓 수량 증가 시 UI를 업데이트한다`() {
        // Given
        val initialCount = reservationPresenter.getTicketCountValue()

        // When
        reservationPresenter.increaseTicketCount()

        // Then
        assertEquals(initialCount + 1, reservationPresenter.getTicketCountValue())
        verify { reservationView.setTicketCounterUi(initialCount + 1) }
    }

    @Test
    fun `티켓 수량 감소 시 UI를 업데이트한다`() {
        // Given
        reservationPresenter.increaseTicketCount()
        val initialCount = reservationPresenter.getTicketCountValue()

        // When
        reservationPresenter.decreaseTicketCount()

        // Then
        assertEquals(initialCount - 1, reservationPresenter.getTicketCountValue())
        verify { reservationView.setTicketCounterUi(initialCount - 1) }
    }

    @Test
    fun `날짜와 시간이 선택된 경우 티켓 UI로 이동한다`() {
        // Given
        val selectedDate = LocalDate.of(2025, 4, 2)
        val selectedTime = LocalTime.of(14, 0)
        reservationPresenter.onChangedDate(selectedDate)
        reservationPresenter.onChangedTime(selectedTime)

        // When
        reservationPresenter.handleCompleteReservation()

        // Then
        verify { reservationView.navigateToSelectSeatView(any()) }
    }

    @Test
    fun `날짜나 시간이 선택되지 않은 경우 오류 메시지를 표시한다`() {
        // When
        reservationPresenter.handleCompleteReservation()

        // Then
        verify { reservationView.printError(ReservationErrorType.NotSelectedDateTime) }
        verify(exactly = 0) { reservationView.navigateToSelectSeatView(any()) }
    }
}
