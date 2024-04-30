package woowacourse.movie.presentation.ui.detail

import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R

class ReservationView(view: View) {
    private val reservationCountTextView: TextView = view.findViewById(R.id.reservationCount)
    private val minusButton: Button = view.findViewById(R.id.minusButton)
    private val plusButton: Button = view.findViewById(R.id.plusButton)
    private val reserveButton: Button = view.findViewById(R.id.reserveButton)
    val dateSpinner: Spinner = view.findViewById(R.id.date_spinner)
    val timeSpinner: Spinner = view.findViewById(R.id.time_spinner)

    fun onClickMinusButton(listener: () -> Unit) {
        minusButton.setOnClickListener {
            listener()
        }
    }

    fun onClickPlusButton(listener: () -> Unit) {
        plusButton.setOnClickListener {
            listener()
        }
    }

    fun onClickReserveButton(listener: () -> Unit) {
        reserveButton.setOnClickListener {
            listener()
        }
    }

    fun updateReservationCount(count: Int) {
        reservationCountTextView.text = count.toString()
    }
}
