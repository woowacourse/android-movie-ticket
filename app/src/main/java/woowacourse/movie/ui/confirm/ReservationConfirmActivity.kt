package woowacourse.movie.ui.confirm

import android.os.Bundle
import android.widget.TextView
import com.example.domain.usecase.DiscountApplyUseCase
import woowacourse.movie.R
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_RESERVATION
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val discountApplyUseCase = DiscountApplyUseCase()

    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val reservationRes = intent.getParcelableExtraCompat<ReservationState>(KEY_RESERVATION)
            ?: return keyError(KEY_RESERVATION)
        setInitReservationData(reservationRes)
    }

    private fun setInitReservationData(
        reservationState: ReservationState
    ) {
        titleTextView.text = reservationState.movieState.title
        dateTextView.text = reservationState.dateTime.format(DATE_TIME_FORMATTER)
        reservationCountTextView.text =
            getString(R.string.person_count_text, reservationState.countState.value)
        setDiscountApplyMoney(reservationState)
    }

    private fun setDiscountApplyMoney(reservationState: ReservationState) =
        discountApplyUseCase(reservationState.asDomain()) {
            moneyTextView.text = DECIMAL_FORMATTER.format(it.value)
        }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
    }
}
