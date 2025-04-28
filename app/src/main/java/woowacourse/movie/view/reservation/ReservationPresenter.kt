package woowacourse.movie.view.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationPresenter(
    private val view: ReservationContract.View,
) : ReservationContract.Presenter {
    private var movie: Movie? = null
    private var reservationCount: ReservationCount = ReservationCount()

    override fun loadData(
        movie: Movie?,
        count: Int?,
        dateTime: String?,
    ) {
        this.movie = movie

        if (this.movie == null) {
            // 다이얼로그 노출
            return
        }

        this.movie?.let { view.showMovieDetail(it) }

        count?.let { reservationCount = ReservationCount(it) }
        dateTime?.let { dateTimeStr ->
            val formatter = DateTimeFormatter.ofPattern(SPINNER_DATETIME_FORMAT)
            val realDateTime = LocalDateTime.parse(dateTimeStr, formatter)
            val times = movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), realDateTime.toLocalDate()) ?: emptyList()
            view.updateTimeSet(times, realDateTime.toLocalTime())
        }

        this.movie?.let {
            view.updateReservationCount(reservationCount.value)
        }
    }

    override fun increaseCount(count: Int) {
        reservationCount += count
        view.updateReservationCount(reservationCount.value)
    }

    override fun decreaseCount(count: Int) {
        reservationCount -= count
        view.updateReservationCount(reservationCount.value)
    }

    override fun selectDate(date: LocalDate) {
        val times = movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), date) ?: emptyList()
        times.ifEmpty {
            val nextDate = date.plusDays(1)
            val dates = movie?.screeningPeriod?.getAvailableDates(LocalDateTime.of(nextDate, LocalTime.of(0, 0))) ?: emptyList()
            if (dates.isEmpty()) {
                view.notifyUnavailableDate()
                return
            }
            view.updateDateSet(dates, nextDate)
        }

        val temp = movie?.screeningPeriod?.getAvailableDates(LocalDateTime.now()) ?: emptyList()
        view.updateDateSet(temp)
        view.updateTimeSet(times)
    }

    override fun onReserve(
        reservationDate: LocalDate?,
        reservationTime: LocalTime?,
    ) {
        if (reservationDate == null || reservationTime == null) {
            view.notifyInvalidReservationInfo()
            return
        }

        val reservationInfo =
            ReservationInfo(
                title = movie?.title ?: "",
                reservationDateTime = LocalDateTime.of(reservationDate, reservationTime),
                reservationCount = reservationCount,
            )

        view.navigateToSeatSelectionScreen(reservationInfo)
    }

    companion object {
        private const val SPINNER_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm"
    }
}
