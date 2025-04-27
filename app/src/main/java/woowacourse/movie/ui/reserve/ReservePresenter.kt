package woowacourse.movie.ui.reserve

import woowacourse.movie.domain.date.scheduler.CurrentDateScheduler
import woowacourse.movie.domain.date.scheduler.CurrentTimeScheduler
import woowacourse.movie.domain.date.scheduler.ReservationScheduler
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.PurchaseCount
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.TicketMachine
import woowacourse.movie.domain.model.TicketType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservePresenter(private val view: ReserveContract.View) : ReserveContract.Presenter {
    private val reservationScheduler =
        ReservationScheduler(CurrentDateScheduler(), CurrentTimeScheduler())
    private var purchaseCount = PurchaseCount(1)
    private val ticketMachine = TicketMachine()
    private lateinit var reservation: Reservation
    private lateinit var movie: Movie

    override fun initMovie(movie: Movie) {
        this.movie = movie
        view.initScreen(movie)
    }

    override fun initReservationData(selectedDateTime: () -> LocalDateTime) {
        reservation = Reservation(movie.title, selectedDateTime(), null)
    }

    override fun initDateSpinner(currentDate: LocalDate) {
        val dates = reservationScheduler.reservableDates(movie.screeningDate, currentDate)
        view.fetchDates(dates)
    }

    override fun initTimeSpinner(
        startDate: LocalDate,
        currentDateTime: LocalDateTime,
    ) {
        val firstDate = reservationScheduler.startDate(startDate, currentDateTime.toLocalDate())
        view.fetchTimes(screeningTimes(firstDate, currentDateTime))
    }

    private fun screeningTimes(
        selectedDate: LocalDate,
        currentDateTime: LocalDateTime,
    ): List<LocalTime> =
        reservationScheduler.reservableTimes(
            selectedDate,
            currentDateTime,
        )

    override fun increasePurchaseCount() {
        purchaseCount = purchaseCount.increase()
        updateTicketCount()
    }

    override fun decreasePurchaseCount() {
        if (purchaseCount.canDecrease()) {
            purchaseCount = purchaseCount.decrease()
            updateTicketCount()
        } else {
            view.showToast()
        }
    }

    override fun updateTicketCount() {
        view.fetchPurchaseCount(purchaseCount.value)
    }

    override fun reserve() {
        val tickets = ticketMachine.tickets(List(purchaseCount.value) { TicketType.DEFAULT })
        reservation.initTickets(tickets)
        view.reserve(reservation, purchaseCount.value)
    }

    override fun dateOnClick(
        date: LocalDate,
        currentDateTime: LocalDateTime,
        selectedDateTime: () -> LocalDateTime,
    ) {
        view.fetchTimes(screeningTimes(date, currentDateTime))
        val screeningTimesSize =
            reservationScheduler.reservableTimes(date, currentDateTime).size
        view.dateOnClick(date, screeningTimesSize)
        reservation = reservation.updateReservedTime(selectedDateTime())
    }

    override fun timeOnClick(
        position: Int,
        selectedDateTime: () -> LocalDateTime,
    ) {
        view.timeOnClick(position)
        reservation = reservation.updateReservedTime(selectedDateTime())
    }
}
