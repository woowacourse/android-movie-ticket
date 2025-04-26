package woowacourse.movie.reserve

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.domain.TimeScheduler
import java.time.LocalDate
import java.time.LocalDateTime

class ReservePresenter(
    private val view: ReserveContract.View,
) : ReserveContract.Presenter {
    lateinit var reservation: Reservation

    override fun initReservation(
        reservation: Reservation?,
        movie: Movie,
    ) {
        this.reservation = reservation ?: Reservation(
            movie = movie,
            _count = TicketCount(),
            reservedTime = getInitSchedule(movie.screeningDate),
        )
    }

    override fun initView() {
        view.initMovieInfo(reservation.movie)
        updateDateSpinner()
        view.initTimeSpinner()
        view.updateTicketCount(reservation.count.toString())
        view.initButtonClickListeners()
    }

    override fun updateDateSpinner() {
        view.initDateSpinner(
            reservation.movie.screeningDate.reservableDates(LocalDate.now()),
            reservation.reservedTime.toLocalDate(),
        )
    }

    override fun updateTimeSpinner(selectedDate: LocalDate) {
        val times =
            TimeScheduler.reservableTimes(
                selectedDate,
                getCurrentTime(),
            )
        view.updateTimeSpinner(times, reservation.reservedTime.toLocalTime())
    }

    override fun onPlusButtonClick() {
        reservation = reservation.plusCount()
        view.updateTicketCount(reservation.count.toString())
    }

    override fun onMinusButtonClick() {
        if (reservation.canMinus()) {
            reservation = reservation.minusCount()
            view.updateTicketCount(reservation.count.toString())
        }
    }

    override fun updateReservedTime(dateTime: LocalDateTime) {
        reservation = reservation.updateReservedTime(dateTime)
    }

    private fun getInitSchedule(screeningDate: ScreeningDate): LocalDateTime {
        val firstDate = screeningDate.reservableDates(LocalDate.now()).first()

        val firstTime =
            TimeScheduler.reservableTimes(
                firstDate,
                getCurrentTime(),
            ).first()

        return LocalDateTime.of(firstDate, firstTime)
    }

    private fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now().plusHours(1).withMinute(0).withSecond(0).withNano(0)
    }
}
