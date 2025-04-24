package woowacourse.movie.presentation.view.reservation.detail

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.toModel
import woowacourse.movie.presentation.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationDetailPresenter(
    private val view: ReservationDetailContract.View,
) : ReservationDetailContract.Presenter {
    private var movie: Movie? = null
    private var reservationCount = ReservationCount()

    override fun fetchData(
        initCount: Int?,
        dateTime: LocalDateTime?,
        getMovie: () -> MovieUiModel?,
    ) {
        movie = getMovie()?.toModel()
        initCount?.let { reservationCount = ReservationCount(it) }

        if (movie == null) {
            view.showNoAvailableTimesDialog()
            return
        }

        setupView(dateTime)
    }

    override fun updateReservationCount(updateCount: Int) {
        reservationCount += updateCount
        view.updateReservationCount(reservationCount.value, reservationCount.isClickable())
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
            ).toUiModel()
        view.navigateToResult(reservationInfo)
    }

    private fun setupView(dateTime: LocalDateTime?) {
        val availableDates = getAvailableDates()
        if (availableDates.isEmpty()) {
            view.showNoAvailableTimesDialog()
            return
        }

        view.setScreen(movie!!.toUiModel())
        view.updateDateSpinner(
            availableDates,
            getAvailableTimesForDate(dateTime?.toLocalDate()),
            dateTime,
        )
        view.updateReservationCount(reservationCount.value, reservationCount.isClickable())
    }

    private fun ReservationCount.isClickable(): Boolean = this.value > ReservationCount.RESERVATION_MIN_COUNT

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
