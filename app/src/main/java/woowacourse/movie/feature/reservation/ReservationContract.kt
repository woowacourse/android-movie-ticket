package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.ticket.TicketData
import woowacourse.movie.model.movieSelect.screening.Screening
import java.time.LocalDate
import java.time.LocalTime

interface ReservationContract {
    interface View {
        fun showScreeningData(screeningData: ScreeningData)

        fun setDateSelectUi(screeningDates: List<LocalDate>)

        fun setTimeSelectUi(
            selectedDate: LocalDate,
            screening: Screening,
        )

        fun setTicketCounterUi(ticketCountValue: Int)

        fun initTicketPlusBtnUi()

        fun initTicketMinusBtnUi()

        fun printError(message: String)

        fun navigateToSelectSeatView(ticketData: TicketData)
    }

    interface Presenter {
        fun recoverReservationData(savedTicketCount: Int)

        fun initReservationView()

        fun onChangedDate(selectedDate: LocalDate)

        fun onChangedTime(selectedTime: LocalTime)

        fun increaseTicketCount()

        fun decreaseTicketCount()

        fun getTicketCountValue(): Int

        fun handleCompleteReservation()
    }
}
