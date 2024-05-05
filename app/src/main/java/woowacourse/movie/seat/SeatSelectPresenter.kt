package woowacourse.movie.seat

import android.widget.TextView
import woowacourse.movie.db.MediaContentsDB
import woowacourse.movie.model.Rank
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Seats

class SeatSelectPresenter(
    private val view: SeatSelectContract.View,
    private val movieId: Int,
    private val reservationSchedule: ReservationSchedule,
    val seats: Seats,
) : SeatSelectContract.Presenter {
    private val totalPrice
        get() = Rank.calculateTotalPrice(getRanks(seats.value))

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
            seats,
            seats.value.joinToString(", "),
            Rank.calculateTotalPrice(getRanks(seats.value)),
            reservationSchedule,
        )
    }

    override fun selectSeat(
        seat: String,
        isSelected: Boolean,
        textView: TextView,
    ) {
        if (isSelected) {
            seats.value.remove(seat)
            view.changeSeatColor(true, textView)
        } else {
            seats.value.add(seat)
            view.changeSeatColor(false, textView)
        }

        view.showTotalPrice(totalPrice)
        view.showReservationCheck(seats.isValidate())
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
