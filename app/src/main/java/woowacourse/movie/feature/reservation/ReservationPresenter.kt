package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.movieSelect.screening.Screening
import woowacourse.movie.model.ticket.TicketCount
import woowacourse.movie.model.ticket.getOrDefault
import woowacourse.movie.model.ticket.getOrThrow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screening: Screening,
) : ReservationContract.Presenter {
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    var ticketCount: TicketCount = TicketCount.Companion.create(1).getOrDefault()

    override fun recoverReservationData(savedTicketCount: Int) {
        ticketCount = TicketCount.Companion.create(savedTicketCount).getOrThrow()
        view.setTicketCounterUi(ticketCount.value)
    }

    override fun initReservationView() {
        // view에 표시할 데이터를 가공해서 전달
        view.showScreeningData(ScreeningData.fromScreening(screening))
        view.setDateSelectUi(screening.getScreeningDates())
        view.setTicketCounterUi(ticketCount.value)
    }

    override fun onChangedDate(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
        view.setTimeSelectUi(selectedDate, screening)
    }

    override fun onChangedTime(selectedTime: LocalTime) {
        this.selectedTime = selectedTime
    }

    override fun increaseTicketCount() {
        if (ticketCount.value >= screening.capacityOfTheater) {
            view.printError(ERROR_OVER_CAPACITY_THEATER)
            return
        }
        ticketCount = ticketCount.increase().getOrDefault()
        view.setTicketCounterUi(ticketCount.value)
    }

    override fun decreaseTicketCount() {
        ticketCount = ticketCount.decrease().getOrDefault()
        view.setTicketCounterUi(ticketCount.value)
    }

    override fun getTicketCountValue(): Int = ticketCount.value

    override fun handleCompleteReservation() {
        if (selectedDate == null || selectedTime == null) {
            view.printError(ERROR_NOT_SELECTED_DATETIME)
            return
        }

        val ticketData =
            TicketData(
                screeningData = ScreeningData.fromScreening(screening),
                showtime = LocalDateTime.of(selectedDate, selectedTime),
                ticketCount = ticketCount.value,
            )
        view.navigateToSelectSeatView(ticketData)
    }

    companion object {
        private const val ERROR_NOT_SELECTED_DATETIME = "예매 정보가 선택되지 않았습니다"
        private const val ERROR_OVER_CAPACITY_THEATER = "극장의 최대 관람 가능 인원을 넘어섰습니다"
    }
}
