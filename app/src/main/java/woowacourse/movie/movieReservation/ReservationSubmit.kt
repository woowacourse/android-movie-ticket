package woowacourse.movie.movieReservation

import android.view.View
import android.widget.Button
import woowacourse.movie.R

class ReservationSubmit(
    view: View,
    onClickListener: () -> Unit,
) {
    private val reservationSubmitButton: Button = view.findViewById(R.id.reservation_complete_button)

    init {
        reservationSubmitButton.setOnClickListener { onClickListener() }
    }
}
