package woowacourse.movie.seat

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Rank
import woowacourse.movie.model.Ticket

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
) : SeatSelectContract.Presenter {
    private val ranks = mutableListOf<Rank>()

    override fun loadMovieTitle() {
        val title = Movies.obtainMovie(movieId).title
        val totalPrice = Rank.calculateTotalPrice(ranks)

        view.showReservationInfo(title, totalPrice)
    }

    override fun selectSeat(
        position: Int,
        onColor: (Int) -> Unit,
    ) {
        ranks.add(getRank(position))
        val totalPrice = Rank.calculateTotalPrice(ranks)
        val isAvailable = ticket.count == ranks.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(false, onColor)
        view.showReservationCheck(isAvailable)
    }

    override fun unselectSeat(
        position: Int,
        onColor: (Int) -> Unit,
    ) {
        ranks.remove(getRank(position))
        val totalPrice = Rank.calculateTotalPrice(ranks)
        val isAvailable = ticket.count == ranks.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(true, onColor)
        view.showReservationCheck(isAvailable)
    }

    private fun getRank(position: Int): Rank =
        when (position / 4) {
            0, 1 -> {
                Rank.A
            }

            2, 3 -> {
                Rank.S
            }

            else -> {
                Rank.B
            }
        }
}
