package woowacourse.movie.presentation.view.reservation.detail

import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ReservationInfo
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
        if (movie == null) {
            view.notifyNoAvailableDates()
            return
        }

        initReservationCount(initCount)
        setupView(dateTime)
    }

    override fun updateReservationCount(updateCount: Int) {
        reservationCount += updateCount
        view.updateReservationCount(reservationCount.value, reservationCount.isValid())
    }

    override fun onSelectDate(
        date: LocalDate,
        selectedTime: LocalTime?,
    ) {
        view.updateTimes(getAvailableTimesForDate(date), selectedTime)
    }

    override fun onReserve(reservationDateTime: LocalDateTime) {
        val currentMovie = movie ?: return
        if (!reservationCount.isValid()) return

        val reservationInfo =
            ReservationInfo(
                title = currentMovie.title,
                reservationDateTime = reservationDateTime,
                reservationCount = reservationCount,
            ).toUiModel()

        view.notifyReservationConfirm(reservationInfo)
    }

    private fun setupView(dateTime: LocalDateTime?) {
        movie?.let {
            view.setScreen(it.toUiModel())
            view.updateReservationCount(reservationCount.value, reservationCount.isValid())
            setAvailableItems(dateTime)
        }
    }

    private fun initReservationCount(initCount: Int?) {
        initCount?.let { count ->
            runCatching { ReservationCount(count) }.onSuccess { reservationCount = it }
        }
    }

    private fun setAvailableItems(selectedDateTime: LocalDateTime?) {
        val availableDates = getAvailableDates()
        if (availableDates.isEmpty()) {
            view.notifyNoAvailableDates()
            return
        }

        val selectedDate = findValidSelectedDate(selectedDateTime, availableDates)
        val availableTimes = selectedDate?.let { getAvailableTimesForDate(it) }.orEmpty()

        view.updateDates(
            availableDates,
            availableTimes,
            selectedDate?.atTime(availableTimes.firstOrNull() ?: LocalTime.MIN),
        )
    }

    private fun findValidSelectedDate(
        selectedDateTime: LocalDateTime?,
        availableDates: List<LocalDate>,
    ): LocalDate? {
        val initialDate = selectedDateTime?.toLocalDate()
        return if (initialDate != null && getAvailableTimesForDate(initialDate).isNotEmpty()) {
            initialDate
        } else {
            availableDates.firstOrNull { getAvailableTimesForDate(it).isNotEmpty() }
        }
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

    private fun ReservationCount.isValid(): Boolean = value > ReservationCount.RESERVATION_MIN_COUNT
}
