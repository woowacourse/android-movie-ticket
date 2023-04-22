package woowacourse.movie.ui.seat

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.domain.usecase.DiscountApplyUseCase
import woowacourse.movie.R
import woowacourse.movie.model.ReservationState
import woowacourse.movie.model.SeatPositionState
import woowacourse.movie.model.TicketsState
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
import woowacourse.movie.util.showAskDialog

class SeatSelectActivity : BackKeyActionBarActivity(), Observer {
    private val discountApplyUseCase = DiscountApplyUseCase()

    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }
    private val confirmView: ConfirmView by lazy { findViewById(R.id.reservation_confirm) }
    private lateinit var reservationState: ReservationState

    private val seatTable: SeatTable by lazy {
        SeatTable(
            window.decorView.rootView,
            reservationState.countState
        )
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_seat_select)

        reservationState =
            intent.getParcelableExtraCompat(KEY_TICKETS) ?: return keyError(KEY_TICKETS)

        titleTextView.text = reservationState.movieState.title

        seatTable.registerObserver(this)
        confirmView.setOnClickListener { navigateShowDialog(seatTable.chosenSeatInfo) }
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
        seatTable.chosenSeatUpdate(restoreState.toList())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            SEAT_RESTORE_KEY,
            ArrayList(seatTable.chosenSeatInfo)
        )
    }

    private fun navigateShowDialog(seats: List<SeatPositionState>) {
        showAskDialog(
            R.string.reservation_confirm,
            R.string.ask_really_reservation,
            R.string.reservation_cancel,
            R.string.reservation_complete
        ) {
            navigateReservationConfirmActivity(seats)
        }
    }

    private fun navigateReservationConfirmActivity(seats: List<SeatPositionState>) {
        val intent = Intent(this, ReservationConfirmActivity::class.java)
        val tickets = TicketsState.from(reservationState, seats)
        intent.putExtra(KEY_TICKETS, tickets)
        startActivity(intent)
    }

    override fun updateSelectSeats(positionStates: List<SeatPositionState>) {
        confirmView.isClickable = (positionStates.size == reservationState.countState.value)

        val tickets = TicketsState(
            reservationState.movieState,
            reservationState.dateTime,
            positionStates.toList()
        )

        val discountApplyMoney = discountApplyUseCase(tickets.asDomain())
        moneyTextView.text = getString(
            R.string.discount_money,
            DecimalFormatters.convertToMoneyFormat(discountApplyMoney.asPresentation())
        )
    }

    companion object {
        private const val SEAT_RESTORE_KEY = "seat_restore_key"
    }
}
