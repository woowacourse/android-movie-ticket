package woowacourse.movie.presentation.view.reservation.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationPresenter(
    private val view: ReservationContract.View,
) : ReservationContract.Presenter {
    private var movie: Movie? = null
    private var reservationCount = ReservationCount()

    override fun fetchData(
        initCount: Int?,
        dateTime: LocalDateTime?,
        getMovie: () -> Movie?,
    ) {
        movie = getMovie()
        initCount?.let { reservationCount = ReservationCount(it) }

        if (movie == null) {
            view.showNoAvailableTimesDialog()
            return
        }

        setupView(dateTime)
    }

    override fun updateReservationCount(updateCount: Int) {
        reservationCount += updateCount
        view.updateReservationCount(reservationCount.value)
    }

    override fun onSelectDate(
        date: LocalDate,
        selectedTime: LocalTime?,
    ) {
        val availableTimes = getAvailableTimesForDate(date)
        view.updateTimeSpinner(availableTimes, selectedTime)
    }

    override fun onReserve(reservationDateTime: LocalDateTime) {
        if (movie == null) return
        if (reservationCount.value < ReservationCount.RESERVATION_MIN_COUNT) return

        val reservationInfo =
            ReservationInfo(
                title = movie!!.title,
                reservationDateTime = reservationDateTime,
                reservationCount = reservationCount,
            )
        view.navigateToResult(reservationInfo)
    }

    private fun setupView(dateTime: LocalDateTime?) {
        val availableDates = getAvailableDates()
        if (availableDates.isEmpty()) {
            view.showNoAvailableTimesDialog()
            return
        }

        view.setScreen(movie!!)
        view.updateDateSpinner(
            availableDates,
            getAvailableTimesForDate(dateTime?.toLocalDate()),
            dateTime,
        )
        view.updateReservationCount(reservationCount.value)
    }

    private fun getAvailableDates(): List<LocalDate> {
        val now = LocalDate.now()
        return movie?.screeningPeriod?.getAvailableDates(now).orEmpty()
    }

    private fun getAvailableTimesForDate(date: LocalDate?): List<LocalTime> {
        if (date == null) return emptyList()
        val now = LocalDateTime.now()
        return movie?.screeningPeriod?.getAvailableTimesFor(now, date).orEmpty()
    }
}
