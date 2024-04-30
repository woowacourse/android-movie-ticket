package woowacourse.movie.activity

import android.widget.TextView
import woowacourse.movie.uiModels.reservation.ReservationBrief

class ReservationBriefViews (
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
