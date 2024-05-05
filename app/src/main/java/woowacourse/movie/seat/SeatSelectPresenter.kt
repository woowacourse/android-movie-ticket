package woowacourse.movie.seat

import woowacourse.movie.db.MediaContentsDB
import woowacourse.movie.model.Rank
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Ticket

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieId: Int,
    private val ticket: Ticket,
    private val reservationSchedule: ReservationSchedule,
    val seats: MutableList<String> = mutableListOf(),
) : SeatSelectContract.Presenter {
    private val totalPrice
        get() = Rank.calculateTotalPrice(getRanks(seats))

    init {
        loadMovieTitle()
    }

    override fun loadSavedData() {
        view.showTotalPrice(totalPrice)
    }

    override fun confirm() {
        view.showConfirmDialog()
    }

    override fun loadMovieTitle() {
        val title = MediaContentsDB.obtainMovie(movieId).title

        view.showReservationInfo(title, totalPrice)
    }

    override fun loadReservationInformation() {
        view.moveToReservationFinished(
            movieId,
            ticket,
            seats.joinToString(", "),
            Rank.calculateTotalPrice(getRanks(seats)),
            reservationSchedule,
        )
    }

    override fun selectSeat(
        seat: String,
        onColor: (Int) -> Unit,
    ) {
        seats.add(seat)
        val isAvailable = ticket.ticketCount == seats.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(false, onColor)
        view.showReservationCheck(isAvailable)
    }

    override fun unselectSeat(
        seat: String,
        onColor: (Int) -> Unit,
    ) {
        seats.remove(seat)
        val isAvailable = ticket.ticketCount == seats.size

        view.showTotalPrice(totalPrice)
        view.changeSeatColor(true, onColor)
        view.showReservationCheck(isAvailable)
    }

    private fun getRanks(seat: List<String>): List<Rank> {
        return seat.map {
            when (it[0]) {
                'C', 'D' -> Rank.S
                'A', 'B' -> Rank.A
                else -> Rank.B
            }
        }
    }
}
