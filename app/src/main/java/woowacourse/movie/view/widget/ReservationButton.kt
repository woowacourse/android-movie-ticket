package woowacourse.movie.view.widget

import android.content.Intent
import android.widget.Button
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.view.ReservationData

class ReservationButton(
    private val button: Button
) {
    fun setOnClickListener(
        makeReservationData: () -> ReservationData
    ) {
        button.setOnClickListener {
            val reservationData = makeReservationData()
            startReservationResultActivity(reservationData)
        }
    }

    private fun startReservationResultActivity(
        reservationData: ReservationData
    ) {
        val intent = Intent(button.context, ReservationResultActivity::class.java)
        intent.putExtra(ReservationData.RESERVATION_EXTRA_NAME, reservationData)
        button.context.startActivity(intent)
    }
}
