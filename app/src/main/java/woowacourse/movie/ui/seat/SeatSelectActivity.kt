package woowacourse.movie.ui.seat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.domain.model.Ticket
import com.example.domain.model.Tickets
import com.example.domain.usecase.DiscountApplyUseCase
import woowacourse.movie.R
import woowacourse.movie.model.ReservationSeat
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.SeatPositionState
import woowacourse.movie.model.mapper.asDomain
import woowacourse.movie.model.mapper.asPresentation
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.DecimalFormatters
import woowacourse.movie.ui.confirm.ReservationConfirmActivity
import woowacourse.movie.ui.customView.ConfirmView
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_TICKETS
import woowacourse.movie.util.getParcelableArrayListCompat
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class SeatSelectActivity : BackKeyActionBarActivity(), Observer {
    private val discountApplyUseCase = DiscountApplyUseCase()

    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val confirmView: ConfirmView by lazy { findViewById(R.id.reservation_confirm) }
    private lateinit var reservationState: ReservationState

    private val seatTableViewSet: SeatTableViewSet by lazy {
        SeatTableViewSet(
            window.decorView.rootView,
            reservationState.countState
        )
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_seat_select)

        reservationState =
            intent.getParcelableExtraCompat(KEY_TICKETS) ?: return keyError(KEY_TICKETS)

        titleTextView.text = reservationState.movieState.title

        seatTableViewSet.registerObserver(this)
        confirmView.setOnClickListener { navigateReservationConfirmActivity(seatTableViewSet.choosedSeatInfo) }
        confirmView.isClickable = false // 클릭리스너를 설정하면 clickable이 자동으로 참이 되기 때문
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreState: ArrayList<SeatPositionState> =
            savedInstanceState.getParcelableArrayListCompat(SEAT_RESTORE_KEY) ?: return keyError(
                SEAT_RESTORE_KEY
            )
        Log.d("mendel", "restore: ${restoreState.joinToString { it.toString() }}")
        seatTableViewSet.chooseSeatUpdate(restoreState.toList())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            SEAT_RESTORE_KEY,
            ArrayList(seatTableViewSet.choosedSeatInfo)
        )
        Log.d("mendel", "store: ${seatTableViewSet.choosedSeatInfo.joinToString { it.toString() }}")
    }

    private fun navigateReservationConfirmActivity(seats: List<SeatPositionState>) {
        val intent = Intent(this, ReservationConfirmActivity::class.java)
        val reservationSeat = ReservationSeat(reservationState, seats)
        intent.putExtra(KEY_TICKETS, reservationSeat)
        startActivity(intent)
    }

    override fun updateSelectSeats(positionState: List<SeatPositionState>) {
        Log.d("mendel", "update - seatSelectActivity: ${positionState.toList()}")
        confirmView.isClickable = (positionState.size == reservationState.countState.value)

        // 이걸 어떻게 해줄 것인지...
        val tickets = Tickets(
            positionState.map {
                Ticket(
                    reservationState.movieState.asDomain(),
                    reservationState.dateTime,
                    it.asDomain()
                )
            }
        )
        discountApplyUseCase(tickets) {
            moneyTextView.text = getString(
                R.string.discount_money,
                DecimalFormatters.convertToMoneyFormat(it.asPresentation())
            )
        }
    }

    companion object {
        private const val SEAT_RESTORE_KEY = "seat_restore_key"
    }
}
