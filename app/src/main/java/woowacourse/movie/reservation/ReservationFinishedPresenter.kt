package woowacourse.movie.reservation

import woowacourse.movie.db.MediaContents
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
    private val seats: String,
    private val totalPrice: Int,
    private val reservationSchedule: ReservationSchedule,
) : ReservationFinishedContract.Presenter {
    init {
        loadMovieInformation()
        loadReservationInformation()
        loadReservationSchedule()
    }

    override fun loadMovieInformation() {
        val movieTitle = MediaContents.obtainMovie(movieId).title

        view.showMovieInformation(movieTitle)
    }

    override fun loadReservationInformation() {
        view.showReservationInformation(
            ticket.count,
            seats,
            totalPrice,
        )
    }

    override fun loadReservationSchedule() {
        view.showReservationSchedule(
            reservationSchedule.screeningDate,
            reservationSchedule.screeningTime,
        )
    }
}
