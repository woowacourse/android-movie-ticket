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

    override fun initDateSpinner() {
        val dates = reservationScheduler.reservableDates(movie.screeningDate, LocalDate.now())
        view.fetchDates(dates)
    }

    override fun initTimeSpinner(startDate: LocalDate) {
        val firstDate = reservationScheduler.startDate(startDate, LocalDate.now())
        view.fetchTimes(screeningTimes(firstDate))
    }

    private fun screeningTimes(selectedDate: LocalDate): List<LocalTime> =
        reservationScheduler.reservableTimes(
            selectedDate,
            LocalDateTime.now(),
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
        view.reserve(reservation)
    }

    override fun dateOnClick(
        date: LocalDate,
        selectedDateTime: () -> LocalDateTime,
    ) {
        view.fetchTimes(screeningTimes(date))
        val screeningTimesSize =
            reservationScheduler.reservableTimes(date, LocalDateTime.now()).size
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
