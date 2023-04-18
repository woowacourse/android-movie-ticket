package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.payment.PaymentType
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity.Companion.RESERVATION_KEY
import woowacourse.movie.model.ReservationInfo
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    private val reservation: ReservationInfo by lazy {
        intent.customGetSerializable(RESERVATION_KEY) as? ReservationInfo
            ?: run {
                Toast.makeText(this, getString(R.string.movie_data_error_message), Toast.LENGTH_SHORT).show()
                finish()
                ReservationInfo.ofError()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

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
