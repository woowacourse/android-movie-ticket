package woowacourse.movie.ui.reserve

import woowacourse.movie.domain.date.scheduler.CurrentDateScheduler
import woowacourse.movie.domain.date.scheduler.CurrentTimeScheduler
import woowacourse.movie.domain.date.scheduler.ReservationScheduler
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.reservation.PurchaseCount
import woowacourse.movie.domain.model.reservation.Reservation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservePresenter(private val view: ReserveContract.View) : ReserveContract.Presenter {
    private val reservationScheduler =
        ReservationScheduler(CurrentDateScheduler(), CurrentTimeScheduler())
    private var purchaseCount = PurchaseCount(1)
    private lateinit var reservation: Reservation
    private lateinit var movie: Movie

    override fun initMovie(movie: Movie) {
        this.movie = movie
        view.initScreen(movie)
    }

    override fun initReservation(selectedDateTime: () -> LocalDateTime) {
        reservation = Reservation(movie.title, selectedDateTime(), null)
    }

    override fun initDates(currentDate: LocalDate) {
        val dates = reservationScheduler.reservableDates(movie.screeningDate, currentDate)
        if (dates.isEmpty()) {
            val nextDayDates =
                reservationScheduler.reservableDates(movie.screeningDate, currentDate.plusDays(1))
            view.fetchDates(nextDayDates)
        } else {
            view.fetchDates(dates)
        }
    }

    override fun initTimes(
        startDate: LocalDate,
        currentDateTime: LocalDateTime,
    ) {
        val firstDate = reservationScheduler.startDate(startDate, currentDateTime.toLocalDate())
        val screeningTimes = screeningTimes(firstDate, currentDateTime)
        if (screeningTimes.isEmpty()) {
            val nextDayDates =
                reservationScheduler.reservableTimes(firstDate, currentDateTime.plusDays(1))
            view.fetchTimes(nextDayDates)
        }
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
        view.reserve(reservation, purchaseCount.value)
    }

    override fun updateSelectedDate(
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

    override fun updateSelectedTime(
        position: Int,
        selectedDateTime: () -> LocalDateTime,
    ) {
        view.timeOnClick(position)
        reservation = reservation.updateReservedTime(selectedDateTime())
    }
}
