package woowacourse.movie.view.reservation.seat

import android.content.Intent
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.model.Seats
import woowacourse.movie.view.Extras
import woowacourse.movie.view.getParcelableExtraCompat

class SeatSelectPresenter(
    val view: SeatSelectContract.View,
) : SeatSelectContract.Presenter {
    private lateinit var movieTicket: MovieTicket
    private var selectedSeats = Seats.create()

    override fun fetchData(intent: Intent) {
        val result = getMovieTicketData(intent)
        if (result == null) {
            view.showErrorDialog()
            return
        }

        movieTicket = result
        view.initReservationInfo(
            movieTicket.title,
            DEFAULT_PRICE,
        )
    }

    override fun onSeatClicked(seatId: String) {
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId)
            view.updateSeatDeselected(seatId)
        } else {
            if (selectedSeats.size >= movieTicket.count) {
                view.showSeatCountError(movieTicket.count)
                return
            }
            selectedSeats.add(seatId)
            view.updateSeatSelected(seatId)
        }

        view.updateTotalPrice(selectedSeats.totalPrice)
        view.updateConfirmButtonState(selectedSeats.size == movieTicket.count)
    }

    override fun createReservationInfo(onCreated: (ReservationInfo) -> Unit) {
        val reservationInfo =
            ReservationInfo(
                title = movieTicket.title,
                date = movieTicket.date,
                time = movieTicket.time,
                seats = selectedSeats.labels(),
                price = selectedSeats.totalPrice,
            )
        onCreated(reservationInfo)
    }

    override fun onConfirmClicked(
        title: String,
        message: String,
    ) {
        view.showReservationDialog(title, message)
    }

    fun getSelectedSeatIds(): List<String> = selectedSeats.labels()

    fun restoreSelectedSeats(ids: List<String>) {
        for (id in ids) {
            selectedSeats.add(id)
            view.updateSeatSelected(id)
        }
        view.updateTotalPrice(selectedSeats.totalPrice)
        view.updateConfirmButtonState(selectedSeats.size == movieTicket.count)
    }

    private fun getMovieTicketData(intent: Intent): MovieTicket? = intent.getParcelableExtraCompat(Extras.TicketData.TICKET_KEY)

    companion object {
        private const val DEFAULT_PRICE = 0
    }
}
