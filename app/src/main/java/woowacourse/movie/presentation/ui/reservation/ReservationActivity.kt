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

    private lateinit var title: TextView
    private lateinit var date: TextView
    private lateinit var count: TextView
    private lateinit var amount: TextView

    override fun initStartView() {
        title = findViewById(R.id.tv_reservation_title)
        date = findViewById(R.id.tv_reservation_date)
        count = findViewById(R.id.tv_reservation_count)
        amount = findViewById(R.id.tv_reservation_amount)
    }

    override fun initBinding() {
        val id = intent.getIntExtra(PUT_EXTRA_KEY_RESERVATION_ID, DEFAULT_RESERVATION_ID)
        presenter.loadReservation(id)
    }

    override fun showReservation(reservation: Reservation) {
        with(reservation) {
            title.text = screen.movie.title
            date.text = screen.date
            count.text = getString(R.string.reserve_count).format(this.ticket.count)
            amount.text =
                getString(R.string.reserve_amount).format(
                    when (Locale.getDefault().country) {
                        Locale.KOREA.country -> DecimalFormat("#,###원").format(totalPrice)
                        else -> {
                            NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
                        }
                    },
                )
        }
    }

    override fun goToBack(message: String) {
        showToastMessage(message)
        finish()
    }

    override fun unexpectedFinish(message: String) {
        showSnackBar(message)
        finish()
    }

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
