package woowacourse.movie.activity.seats

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.domain.reservation.TicketOffice
import woowacourse.movie.domain.seat.Column
import woowacourse.movie.domain.seat.Row
import woowacourse.movie.uimodel.ReservationModel.Companion.RESERVATION_INTENT_KEY
import woowacourse.movie.uimodel.ReservationOptionModel
import woowacourse.movie.uimodel.ReservationOptionModel.Companion.RESERVATION_OPTION_INTENT_KEY
import woowacourse.movie.uimodel.toReservationModel

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }
    private val seatsView by lazy {
        SeatsView(
            binding,
            reservationOptionModel.ticketCount,
            reservationOptionModel.screeningDateTime
        )
    }

    private val reservationOptionModel by lazy {
        intent.getSerializableExtra(
            RESERVATION_OPTION_INTENT_KEY
        ) as ReservationOptionModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.movieNameTextView.text = reservationOptionModel.movieModel.name.value

        seatsView.set(Row.INDEX_MAXIMUM + 1, Column.INDEX_MAXIMUM + 1)
        binding.reservationCompleteTextView.setOnClickListener {
            completeButtonClickEvent(reservationOptionModel.ticketCount)
        }
    }

    private fun completeButtonClickEvent(ticketCount: Int) {
        if (seatsView.getSelectedCount() == ticketCount) setDialog(ticketCount)
    }

    private fun setDialog(ticketCount: Int) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reservation_confirmation_title))
            .setMessage(getString(R.string.reservation_confirmation_text))
            .setPositiveButton(getString(R.string.reservation_confirmation_positive_button)) { _, _ ->
                positiveButtonClickEvent(ticketCount)
            }
            .setNegativeButton(getString(R.string.reservation_confirmation_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun positiveButtonClickEvent(ticketCount: Int) {
        val nextIntent = Intent(this, ReservationResultActivity::class.java)

        val reservationModel = TicketOffice.generateTicket(
            reservationOptionModel.movieModel.toDomain(),
            reservationOptionModel.screeningDateTime,
            seatsView.getSelectedSeats()
        ).toReservationModel()

        nextIntent.putExtra(RESERVATION_INTENT_KEY, reservationModel)
        startActivity(nextIntent)
        finish()
    }
}
