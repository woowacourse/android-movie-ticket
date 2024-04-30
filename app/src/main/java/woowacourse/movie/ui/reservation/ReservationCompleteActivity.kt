package woowacourse.movie.ui.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.ui.Currency
import java.util.Locale

class ReservationCompleteActivity : AppCompatActivity(), ReservationContract.View {
    private val presenter: ReservationContract.Presenter by lazy { ReservationPresenter(this, DummyReservation) }

    private val title: TextView by lazy { findViewById(R.id.tv_reservation_title) }
    private val date: TextView by lazy { findViewById(R.id.tv_reservation_date) }
    private val time: TextView by lazy { findViewById(R.id.tv_reservation_time) }

    private val count: TextView by lazy { findViewById(R.id.tv_reservation_count) }
    private val reservedSeats: TextView by lazy { findViewById(R.id.tv_reserved_seats) }
    private val amount: TextView by lazy { findViewById(R.id.tv_reservation_amount) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_complete)

        initView()
    }

    private fun initView() {
        val id = intent.getIntExtra(PUT_EXTRA_KEY_RESERVATION_ID, DEFAULT_RESERVATION_ID)
        presenter.loadReservation(id)
    }

    override fun showReservation(reservation: Reservation) {
        with(reservation) {
            title.text = screen.movie.title
            count.text = getString(R.string.reserve_count).format(this.ticket.count)
            date.text = dateTime?.date.toString()
            time.text = dateTime?.time.toString()
            amount.text = currency()
            reservedSeats.text = seats.seats.joinToString(separator = ",") { "${'A' + it.position.row}${it.position.col + 1}" }
        }
    }

    private fun Reservation.currency(): String =
        getString(R.string.reserve_amount, Currency.of(Locale.getDefault().country).format(seats.totalPrice()))

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
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
            val intent = Intent(context, ReservationCompleteActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}
