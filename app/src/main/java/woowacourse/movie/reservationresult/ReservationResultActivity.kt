package woowacourse.movie.reservationresult

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.payment.PaymentType
import woowacourse.movie.R
import woowacourse.movie.getIntentData
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.seatselection.ScreeningSeatSelectionActivity.Companion.RESERVATION_RESULT_KEY
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    private lateinit var reservation: ReservationInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        reservation = getIntentData(RESERVATION_RESULT_KEY) ?: ReservationInfo.ofError()
        initReservationResultView()
    }

    private fun initReservationResultView() {
        val movieNameTextView: TextView = findViewById(R.id.result_movie_name_text_view)
        val paymentAmountTextView: TextView = findViewById(R.id.result_payment_amount_text_view)
        val screeningDateTimeTextView: TextView =
            findViewById(R.id.result_screening_date_time_text_view)
        val ticketCountTextView: TextView = findViewById(R.id.result_ticket_count_text_view)

        with(reservation) {
            val dateFormat: DateTimeFormatter =
                DateTimeFormatter.ofPattern(getString(R.string.reservation_date_time_form))

            movieNameTextView.text = movieName
            screeningDateTimeTextView.text = screeningDateTime.format(dateFormat)
            ticketCountTextView.text = getString(R.string.ticket_count_form).format(ticketCount)
            paymentAmountTextView.text = getString(R.string.payment_amount_form).format(
                DecimalFormat(getString(R.string.payment_amount_unit_form)).format(paymentAmount),
                getPaymentTypeString(paymentType)
            )
        }
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String = when (paymentType) {
        PaymentType.LOCAL_PAYMENT -> getString(R.string.payment_type_local_text)
        PaymentType.ERROR_PAID -> getString(R.string.payment_error_message)
    }
}
