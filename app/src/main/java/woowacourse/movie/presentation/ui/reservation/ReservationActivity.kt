package woowacourse.movie.presentation.ui.reservation

import android.content.Context
import android.content.Intent
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.reservation.ReservationContract.Presenter
import woowacourse.movie.presentation.ui.reservation.ReservationContract.View
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

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
            title.text = screen.movie.title
            date.text = screen.date
            count.text = getString(R.string.reserve_count).format(this.ticket.count)
            amount.text = currency()
        }
    }

    private fun Reservation.currency(): String {
        val amount =
            when (Locale.getDefault().country) {
                Locale.KOREA.country -> getString(R.string.price_format_kor, totalPrice)
                else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
            }

        return getString(R.string.reserve_amount, amount)
    }

    override fun back() = finish()

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
