package woowacourse.movie.ui.confirm

import android.os.Bundle
import android.widget.TextView
import com.example.domain.usecase.DiscountApplyUseCase
import woowacourse.movie.R
import woowacourse.movie.model.ReservationSeat
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.model.mapper.asPresentation
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.DecimalFormatters
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_TICKETS
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class ReservationConfirmActivity : BackKeyActionBarActivity() {
    private val discountApplyUseCase = DiscountApplyUseCase()
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.reservation_date) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val reservationCountTextView: TextView by lazy { findViewById(R.id.reservation_count_and_seat) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_reservation_confirm)
        val reservationSeat = intent.getParcelableExtraCompat<ReservationSeat>(KEY_TICKETS)
            ?: return keyError(KEY_TICKETS)
        setInitReservationData(reservationSeat)
    }

    private fun setInitReservationData(
        reservationSeat: ReservationSeat
    ) {
        titleTextView.text = reservationSeat.reservationState.movieState.title
        dateTextView.text =
            DateTimeFormatters.convertToDateTime(reservationSeat.reservationState.dateTime)
        reservationCountTextView.text =
            getString(
                R.string.person_count_and_seat,
                reservationSeat.reservationState.countState.value,
                reservationSeat.seats.joinToString { it.toString() }
            )
        setDiscountApplyMoney(reservationSeat)
    }

    private fun setDiscountApplyMoney(reservationSeat: ReservationSeat) =
        discountApplyUseCase(reservationSeat.asDomain()) {
            moneyTextView.text = DecimalFormatters.convertToMoneyFormat(it.asPresentation())
        }
}
