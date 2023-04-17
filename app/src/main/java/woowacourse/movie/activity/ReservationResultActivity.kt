package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.payment.PaymentType
import woowacourse.movie.uimodel.ReservationModel
import woowacourse.movie.util.RESERVATION_INTENT_KEY
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    private val movieNameTextView: TextView by lazy { findViewById(R.id.result_movie_name_text_view) }
    private val paymentAmountTextView: TextView by lazy { findViewById(R.id.result_payment_amount_text_view) }
    private val screeningDateTimeTextView: TextView by lazy { findViewById(R.id.result_screening_date_time_text_view) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.result_ticket_count_text_view) }
    private val reservationModel: ReservationModel? by lazy { intent.getSerializableExtra(RESERVATION_INTENT_KEY) as? ReservationModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        initReservationResultView()
    }

    private fun initReservationResultView() {
        if (reservationModel == null) return
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

        movieNameTextView.text = reservationModel!!.movieModel.name.value
        screeningDateTimeTextView.text = reservationModel!!.screeningDateTime.format(dateFormat)

        ticketCountTextView.text = getString(R.string.ticket_count_form).format(reservationModel!!.ticketCount)

        paymentAmountTextView.text = getString(R.string.payment_amount_form).format(
            DecimalFormat("#,###").format(reservationModel!!.paymentAmount.value),
            getPaymentTypeString(reservationModel!!.paymentType)
        )
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String = when (paymentType) {
        PaymentType.LOCAL_PAYMENT -> getString(R.string.payment_type_local_text)
    }
}
