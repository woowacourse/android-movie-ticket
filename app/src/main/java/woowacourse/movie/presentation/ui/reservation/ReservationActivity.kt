package woowacourse.movie.presentation.ui.reservation

import android.content.Context
import android.content.Intent
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.reservation.ReservationContract.Presenter
import woowacourse.movie.presentation.ui.reservation.ReservationContract.View
import woowacourse.movie.presentation.utils.currency
import woowacourse.movie.presentation.utils.toScreeningDate

class ReservationActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_reservation
    override val presenter: Presenter by lazy { ReservationPresenter(this, DummyReservation) }

    private val title: TextView by lazy { findViewById(R.id.tv_reservation_title) }
    private val date: TextView by lazy { findViewById(R.id.tv_reservation_date) }
    private val count: TextView by lazy { findViewById(R.id.tv_reservation_count) }
    private val amount: TextView by lazy { findViewById(R.id.tv_reservation_amount) }

    override fun initStartView() {
        val id = intent.getIntExtra(PUT_EXTRA_KEY_RESERVATION_ID, DEFAULT_RESERVATION_ID)
        presenter.loadReservation(id)
    }

    override fun showReservation(reservation: Reservation) {
        with(reservation) {
            title.text = movie.title
            date.text = dateTime.toScreeningDate()
            count.text = getString(R.string.reserve_count, ticketCount, seats.toSeatString())
            amount.text = totalPrice.currency(this@ReservationActivity)
        }
    }

    override fun back() = finish()

    private fun List<Seat>.toSeatString(): String = this.joinToString(", ") { "${it.column}${it.row}" }

    companion object {
        private const val PUT_EXTRA_KEY_RESERVATION_ID = "reservationId"
        private const val DEFAULT_RESERVATION_ID = -1

        fun startActivity(
            context: Context,
            reservationId: Int,
        ) {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}
