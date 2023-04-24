package woowacourse.movie.activity.seats

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.domain.payment.PaymentAmount
import woowacourse.movie.domain.reservation.TicketCount
import woowacourse.movie.domain.seat.Column
import woowacourse.movie.domain.seat.Row
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
    private val seatsView by lazy { SeatsView(binding, seatsRowRange, seatsColumnRange, ticketCount, screeningDateTime) }

    private val movieModel by lazy { intent.getSerializableExtra(MOVIE_INTENT_KEY) as MovieModel }
    private val ticketCount by lazy { intent.getIntExtra(TICKET_COUNT_INTENT_KEY, TicketCount.MINIMUM) }
    private val screeningDateTime: LocalDateTime by lazy { intent.getSerializableExtra(SCREENING_DATE_TIME_INTENT_KEY) as LocalDateTime }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        val nextIntent = Intent(this, ReservationResultActivity::class.java)
        val paymentAmountText = binding.paymentAmountTextView.text.toString()
        val paymentAmountString = paymentAmountText.replace(",", "").replace("원", "")
        val paymentAmount = PaymentAmount(paymentAmountString.toInt())

        val reservationModel = ReservationModel(
            movie = movieModel,
            screeningDateTime = screeningDateTime,
            ticketCount = ticketCount,
            seats = seatsView.getSelectedSeats().map { it.toSeatModel() },
            paymentAmount = paymentAmount
        )

        nextIntent.putExtra(RESERVATION_INTENT_KEY, reservationModel)
        startActivity(nextIntent)
        finish()
    }

    companion object {
        val seatsRowRange: CharRange = Row.MINIMUM..Row.MAXIMUM
        val seatsColumnRange: IntRange = Column.MINIMUM..Column.MAXIMUM
    }
}
