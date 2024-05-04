package woowacourse.movie.reservation

import woowacourse.movie.db.MediaContents
import woowacourse.movie.model.Ticket

class ReservationFinishedPresenter(
    private val view: ReservationFinishedContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
    private val seats: String,
    private val totalPrice: Int,
) : ReservationFinishedContract.Presenter {
    init {
        loadReservationInformation()
    }

    override fun loadReservationInformation() {
        val movieTitle = MediaContents.obtainMovie(movieId).title

        view.showReservationInformation(
            movieTitle,
            ticket.screeningDate,
            ticket.screeningTime,
            ticket.count,
            seats,
            totalPrice,
        )
    }
}
