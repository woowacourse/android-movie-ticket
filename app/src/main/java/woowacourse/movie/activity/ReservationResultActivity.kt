package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.payment.PaymentType
import woowacourse.movie.R
import woowacourse.movie.activity.ReservationActivity.Companion.RESERVATION_KEY
import woowacourse.movie.model.ActivityReservationModel
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        initReservationResultView()
    }

    private fun initReservationResultView() {
        val movieMovieNameTextView: TextView = findViewById(R.id.result_movie_name_text_view)
        val paymentAmountTextView: TextView = findViewById(R.id.result_payment_amount_text_view)
        val screeningDateTimeTextView: TextView = findViewById(R.id.result_screening_date_time_text_view)
        val ticketCountTextView: TextView = findViewById(R.id.result_ticket_count_text_view)
        val reservation: ActivityReservationModel =
            intent.getSerializableExtra(RESERVATION_KEY) as ActivityReservationModel?
                ?: throw IllegalArgumentException(RESERVATION_DATA_ERROR)

        with(reservation) {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

            movieMovieNameTextView.text = movieName
            screeningDateTimeTextView.text = screeningDateTime.format(dateFormat)
            ticketCountTextView.text = getString(R.string.ticket_count_form).format(ticketCount)
            paymentAmountTextView.text = getString(R.string.payment_amount_form).format(
                DecimalFormat("#,###").format(paymentAmount.value),
                getPaymentTypeString(paymentType)
            )
        }
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String = when (paymentType) {
        PaymentType.LOCAL_PAYMENT -> getString(R.string.payment_type_local_text)
    }

    companion object {
        private const val RESERVATION_DATA_ERROR = "[ERROR] 예약 정보를 받아올 수 없습니다."
    }
}
