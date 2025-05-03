package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.model.ticket.getOrDefault
import woowacourse.movie.view.model.TicketData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationPresenter(
    private val view: ReservationContract.View,
) {
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private val screeningData: ScreeningData by lazy { view.getScreeningData() }
    private val screening: Screening by lazy { screeningData.toScreening() }
    var ticketCount: TicketCount = TicketCount.Companion.create(1).getOrDefault()
    var timeItemPosition: Int = 0

    fun initReservationData(
        savedTicketCount: Int,
        savedTimeItemPosition: Int,
    ) {
        ticketCount = TicketCount.Companion.create(savedTicketCount).getOrDefault()
        timeItemPosition = savedTimeItemPosition
    }

    fun initReservationUI() {
        view.initScreeningInfoUI(screeningData)
        view.setDateSelectUi(screening)
        view.setTimeSelectUi(screening.period.start, screening, timeItemPosition)
        view.setTicketCounterUi(ticketCount)
    }

    fun onChangedDate(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
        view.setTimeSelectUi(selectedDate, screening, timeItemPosition)
    }

    fun onChangedTime(selectedTime: LocalTime) {
        this.selectedTime = selectedTime
    }

    fun increaseTicketCount() {
        if (ticketCount.value >= screening.capacityOfTheater) {
            view.printError(ERROR_OVER_CAPACITY_THEATER)
            return
        }
        ticketCount = ticketCount.increase().getOrDefault()
        view.setTicketCounterUi(ticketCount)
    }

    fun decreaseTicketCount() {
        ticketCount = ticketCount.decrease().getOrDefault()
        view.setTicketCounterUi(ticketCount)
    }

    fun navigateToSelectSeatUI() {
        if (selectedDate == null || selectedTime == null) {
            view.printError(ERROR_NOT_SELECTED_DATETIME)
            return
        }

        val ticketData =
            TicketData(
                screeningData = screeningData,
                showtime = LocalDateTime.of(selectedDate, selectedTime),
                ticketCount = ticketCount.value,
            )
        view.navigateToSelectSeatUI(ticketData)
    }

    companion object {
        private const val ERROR_NOT_SELECTED_DATETIME = "예매 정보가 선택되지 않았습니다"
        private const val ERROR_OVER_CAPACITY_THEATER = "극장의 최대 관람 가능 인원을 넘어섰습니다"
    }
}
