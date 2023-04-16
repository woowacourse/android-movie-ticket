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

    private val movieMovieNameTextView: TextView by lazy { findViewById(R.id.result_movie_name_text_view) }
    private val paymentAmountTextView: TextView by lazy { findViewById(R.id.result_payment_amount_text_view) }
    private val screeningDateTimeTextView: TextView by lazy { findViewById(R.id.result_screening_date_time_text_view) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.result_ticket_count_text_view) }
    private val reservation: ActivityReservationModel by lazy {
        intent.getSerializableExtra(RESERVATION_KEY) as ActivityReservationModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        initReservationResultView()
    }

    private fun initReservationResultView() {
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
}
