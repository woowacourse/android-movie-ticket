package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.payment.PaymentType
import domain.reservation.Reservation
import woowacourse.movie.R
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    private val movieNameTextView: TextView by lazy { findViewById<TextView>(R.id.result_movie_name_text_view) }
    private val paymentAmountTextView: TextView by lazy { findViewById<TextView>(R.id.result_payment_amount_text_view) }
    private val screeningDateTimeTextView: TextView by lazy { findViewById<TextView>(R.id.result_screening_date_time_text_view) }
    private val ticketCountTextView: TextView by lazy { findViewById<TextView>(R.id.result_ticket_count_text_view) }
    private val reservation: Reservation by lazy { intent.getSerializableExtra("reservation") as Reservation }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        initReservationResultView()
    }

    private fun initReservationResultView() {
        with(reservation) {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

            movieNameTextView.text = movie.name
            screeningDateTimeTextView.text = screeningDateTime.format(dateFormat)

            ticketCountTextView.text = TICKET_COUNT.format(ticketCount)

            paymentAmountTextView.text = PAYMENT_AMOUNT.format(
                DecimalFormat("#,###").format(paymentAmount.value),
                getPaymentTypeString(paymentType)
            )
        }
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String = when (paymentType) {
        PaymentType.LOCAL_PAYMENT -> LOCAL_PAYMENT_TEXT
    }

    companion object {
        private const val TICKET_COUNT = "일반 %d명"
        private const val PAYMENT_AMOUNT = "%s원 (%s)"
        private const val LOCAL_PAYMENT_TEXT = "현장 결제"
    }
}
