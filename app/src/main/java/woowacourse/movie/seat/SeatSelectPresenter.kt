package woowacourse.movie.seat

import woowacourse.movie.db.Movies
import woowacourse.movie.model.Rank
import woowacourse.movie.model.Ticket

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
) : SeatSelectContract.Presenter {
    private val seats = mutableListOf<String>()

    override fun confirm() {
        view.showConfirmDialog()
    }

    override fun loadMovieTitle() {
        val title = Movies.obtainMovie(movieId).title
        val totalPrice = Rank.calculateTotalPrice(getRanks(seats))

        view.showReservationInfo(title, totalPrice)
    }

    override fun loadReservationInformation() {
        view.moveToReservationFinished(
            movieId,
            ticket,
            seats.joinToString(", "),
            Rank.calculateTotalPrice(getRanks(seats)),
        )
    }

    override fun selectSeat(
        seat: String,
        onColor: (Int) -> Unit,
    ) {
        seats.add(seat)
        val totalPrice = Rank.calculateTotalPrice(getRanks(seats))
        val isAvailable = ticket.count == seats.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(false, onColor)
        view.showReservationCheck(isAvailable)
    }

    override fun unselectSeat(
        seat: String,
        onColor: (Int) -> Unit,
    ) {
        seats.remove(seat)
        val totalPrice = Rank.calculateTotalPrice(getRanks(seats))
        val isAvailable = ticket.count == seats.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(true, onColor)
        view.showReservationCheck(isAvailable)
    }

    private fun getRanks(seat: List<String>): List<Rank> {
        return seat.map {
            when (it[0]) {
                'S' -> Rank.S
                'A' -> Rank.A
                else -> Rank.B
            }
        }
    }
}
