package woowacourse.movie.view.reservation

import android.os.Bundle
import woowacourse.movie.data.DummyMovie
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
    private var reservationCount: ReservationCount = ReservationCount()
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null

    private var availableDates: List<LocalDate> = emptyList()

    fun getCurrentReservationInfo(): ReservationInfo? {
        val currentMovie = this.movie ?: return null
        val currentDate = this.selectedDate ?: return null
        val currentTime = this.selectedTime ?: return null

        return ReservationInfo(
            title = currentMovie.title,
            reservationDateTime = LocalDateTime.of(currentDate, currentTime),
            reservationCount = this.reservationCount,
        )
    }

    override fun loadData(
        movie: Movie?,
        savedInstanceState: Bundle?,
    ) {
        val restoredReservationInfo =
            savedInstanceState?.getParcelable<ReservationInfo>(
                ReservationActivity.RESTORE_BUNDLE_KEY_RESERVATION_INFO,
            )

        if (restoredReservationInfo != null) {
            this.movie = DummyMovie.dummyMovie.find { it.title == restoredReservationInfo.title }
            this.reservationCount = restoredReservationInfo.reservationCount
            this.selectedDate = restoredReservationInfo.reservationDateTime.toLocalDate()
            this.selectedTime = restoredReservationInfo.reservationDateTime.toLocalTime()

            this.availableDates = this.movie?.screeningPeriod?.getAvailableDates(LocalDateTime.now()) ?: emptyList()

            this.movie?.let { view.showMovieDetail(it) }
            val availableDates = this.movie?.screeningPeriod?.getAvailableDates(LocalDateTime.now()) ?: emptyList()
            view.updateDateSet(availableDates, this.selectedDate)

            val availableTimes = this.movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), this.selectedDate!!) ?: emptyList()
            view.updateTimeSet(availableTimes, this.selectedTime)

            view.updateReservationCount(this.reservationCount.value)

            return
        }

        this.movie = movie

        if (this.movie == null) {
            view.notifyInvalidReservationInfo()
            return
        }

        this.reservationCount = ReservationCount()
        this.selectedDate = null
        this.selectedTime = null

        this.movie?.let { view.showMovieDetail(it) }

        this.availableDates = this.movie?.screeningPeriod?.getAvailableDates(LocalDateTime.now()) ?: emptyList()

        val initialDate = LocalDate.now()
        val initialTimes = this.movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), initialDate) ?: emptyList()

        view.updateDateSet(this.availableDates, initialDate)
        view.updateTimeSet(initialTimes, initialTimes.firstOrNull())

        this.selectedDate = initialDate
        this.selectedTime = initialTimes.firstOrNull()

        view.updateReservationCount(this.reservationCount.value)
    }

    override fun increaseCount(count: Int) {
        runCatching {
            reservationCount += count
            view.updateReservationCount(reservationCount.value)
        }.onFailure {
            view.notifyCountConstraintError(ReservationCount.MINIMUM_RESERVATION_COUNT)
        }
    }

    override fun decreaseCount(count: Int) {
        runCatching {
            reservationCount -= count
            view.updateReservationCount(reservationCount.value)
        }.onFailure {
            view.notifyCountConstraintError(ReservationCount.MINIMUM_RESERVATION_COUNT)
        }
    }

    override fun selectDate(date: LocalDate) {
        this.selectedDate = date
        this.selectedTime = null

        var times = movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), date) ?: emptyList()

        if (times.isEmpty()) {
            val selectedDateIndex = availableDates.indexOf(date)

            var foundNextAvailableDateTime = false

            if (selectedDateIndex != -1) {
                for (i in selectedDateIndex + 1 until availableDates.size) {
                    val nextDate = availableDates[i]
                    val nextTimes = movie?.screeningPeriod?.getAvailableTimesFor(LocalDateTime.now(), nextDate) ?: emptyList() // 그다음 날짜의 시간 목록 확인

                    if (nextTimes.isNotEmpty()) {
                        this.selectedDate = nextDate
                        this.selectedTime = nextTimes.first()
                        times = nextTimes

                        view.updateDateSet(availableDates, this.selectedDate)
                        view.updateTimeSet(times, this.selectedTime)

                        foundNextAvailableDateTime = true
                        break
                    }
                }
            }

            if (!foundNextAvailableDateTime) {
                view.notifyNoFutureAvailability()

                view.updateTimeSet(emptyList(), null)
                this.selectedTime = null
            }
        } else {
            view.updateTimeSet(times, times.firstOrNull())
            this.selectedTime = times.firstOrNull()
        }
    }

    override fun onReserve() {
        val finalReservationDate = this.selectedDate
        val finalReservationTime = this.selectedTime
        val finalMovie = this.movie

        if (finalReservationDate == null || finalReservationTime == null || finalMovie == null) {
            view.notifyInvalidReservationInfo()
            return
        }

        val reservationInfo =
            ReservationInfo(
                title = finalMovie.title,
                reservationDateTime = LocalDateTime.of(finalReservationDate, finalReservationTime),
                reservationCount = reservationCount,
            )

        view.navigateToSeatSelectionScreen(reservationInfo)
    }

    companion object {
        private const val SPINNER_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm"
    }
}
