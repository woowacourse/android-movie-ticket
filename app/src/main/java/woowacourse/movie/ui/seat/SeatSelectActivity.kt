package woowacourse.movie.ui.seat

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.ReservationState
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_RESERVATION
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class SeatSelectActivity : BackKeyActionBarActivity() {

    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_seat_select)

        val reservationState =
            intent.getParcelableExtraCompat<ReservationState>(KEY_RESERVATION) ?: return keyError(
                KEY_RESERVATION
            )

        titleTextView.text = reservationState.movieState.title
    }
}
