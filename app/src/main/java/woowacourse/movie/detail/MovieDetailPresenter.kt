package woowacourse.movie.detail

import woowacourse.movie.db.MediaContentsDB
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Seats
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val movieId: Int,
    val seats: Seats = Seats(),
    private val reservationSchedule: ReservationSchedule = ReservationSchedule(),
) : MovieDetailContract.Presenter {
    init {
        loadMovie()
        loadScreeningDates()
    }

    override fun loadSavedData() {
        view.showCount(seats.ticketCount)
    }

    override fun loadMovie() {
        view.showMovieInformation(MediaContentsDB.obtainMovie(movieId))
    }

    override fun updateScreeningDate(screeningDate: LocalDate) {
        reservationSchedule.updateScreeningDate(screeningDate)

        view.showScreeningTimes(
            reservationSchedule.obtainScreeningTimes(screeningDate).map { "$it:00" },
        )
    }

    override fun updateScreeningTime(screeningTime: String) {
        val (hour, minute) = screeningTime.split(":").map(String::toInt)

        reservationSchedule.updateScreeningTime(LocalTime.of(hour % 24, minute))
    }

    override fun loadScreeningDates() {
        val movie = MediaContentsDB.obtainMovie(movieId)

        view.showScreeningDates(
            reservationSchedule.obtainScreeningDates(
                movie.firstScreeningDate,
                movie.lastScreeningDate,
            ),
        )
    }

    override fun increaseCount() {
        seats.increaseCount()
        view.showCount(seats.ticketCount)
    }

    override fun decreaseCount() {
        seats.decreaseCount()
        view.showCount(seats.ticketCount)
    }

    override fun deliverReservationInformation() {
        val movieTitle = MediaContentsDB.obtainMovie(movieId).title

        view.moveToSeatSelect(movieTitle, seats, reservationSchedule)
    }
}
