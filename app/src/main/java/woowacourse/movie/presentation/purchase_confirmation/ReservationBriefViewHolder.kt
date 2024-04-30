package woowacourse.movie.presentation.purchase_confirmation

import android.widget.TextView
import woowacourse.movie.uimodel.reservation.ReservationBrief

class ReservationBriefViewHolder (
    private val title: TextView,
    private val screeningDateTime: TextView,
    private val positions: TextView,
    private val price: TextView,
){
    fun set(reservationBrief: ReservationBrief){
        title.text = reservationBrief.movieTitle
        screeningDateTime.text = reservationBrief.screeningDateTime
        positions.text = reservationBrief.positions
        price.text = reservationBrief.price
    }
}
