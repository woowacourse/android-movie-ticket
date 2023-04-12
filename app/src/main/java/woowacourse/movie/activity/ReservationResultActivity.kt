package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.PaymentType
import domain.Reservation
import woowacourse.movie.R
import java.text.DecimalFormat

class ReservationResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        val movieNameTextView: TextView = findViewById<TextView>(R.id.result_movie_name_text_view)
        val paymentAmountTextView: TextView = findViewById<TextView>(R.id.result_payment_amount_text_view)
        val screeningDateTextView: TextView = findViewById<TextView>(R.id.result_screening_date_text_view)
        val ticketCountTextView: TextView = findViewById<TextView>(R.id.result_ticket_count_text_view)

        val reservation: Reservation = intent.getSerializableExtra("reservation") as Reservation

        with(reservation) {
            movieNameTextView.text = movie.name
            screeningDateTextView.text = SCREENING_TIME.format(
                movie.screeningDate.year,
                movie.screeningDate.monthValue,
                movie.screeningDate.dayOfMonth
            )

            ticketCountTextView.text = TICKET_COUNT.format(ticketCount)

            paymentAmountTextView.text = PAYMENT_AMOUNT.format(
                DecimalFormat("#,###").format(paymentAmount.value),
                getPaymentTypeString(paymentType)
            )
        }
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String = when (paymentType) {
        PaymentType.LOCAL_PAYMENT -> "현장 결제"
    }

    companion object {
        private const val TICKET_COUNT = "일반 %d명"
        private const val SCREENING_TIME = "상영일: %d.%d.%d"
        private const val PAYMENT_AMOUNT = "%s원 (%s)"
    }
}
