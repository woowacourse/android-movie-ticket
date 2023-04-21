package woowacourse.movie.activity.seats

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import payment.PaymentAmount
import reservation.TicketCount
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel
import woowacourse.movie.uimodel.ReservationModel.Companion.RESERVATION_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_TIME_INTENT_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INTENT_KEY
import woowacourse.movie.uimodel.toSeatModel
import java.time.LocalDateTime

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }
    private val seatsView by lazy { SeatsView(binding, intent) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel = intent.getSerializableExtra(MOVIE_INTENT_KEY) as MovieModel
        val ticketCount: Int = intent.getIntExtra(TICKET_COUNT_INTENT_KEY, TicketCount.MINIMUM)
        binding.movieNameTextView.text = movieModel.name.value

        seatsView.set()
        binding.reservationCompleteTextView.setOnClickListener {
            completeButtonClickEvent(ticketCount)
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
        val movieModel: MovieModel =
            intent.getSerializableExtra(MOVIE_INTENT_KEY) as MovieModel
        val screeningDateTime: LocalDateTime =
            intent.getSerializableExtra(SCREENING_DATE_TIME_INTENT_KEY) as LocalDateTime

        val nextIntent = Intent(this, ReservationResultActivity::class.java)
        val paymentAmount =
            PaymentAmount(binding.paymentAmountTextView.text.toString().toInt())

        val reservationModel = ReservationModel(
            movie = movieModel,
            screeningDateTime = screeningDateTime,
            ticketCount = ticketCount,
            seats = seatsView.getSelectedSeats().map { it.toSeatModel() },
            paymentAmount = paymentAmount
        )

        nextIntent.putExtra(RESERVATION_INTENT_KEY, reservationModel)
        startActivity(nextIntent)
    }
}
