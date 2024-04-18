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
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val presenter: ReservationContract.Presenter by lazy { ReservationPresenter(this, DummyReservation) }

    private lateinit var title: TextView
    private lateinit var date: TextView
    private lateinit var count: TextView
    private lateinit var amount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initBinding()
        initView()
    }

    private fun initBinding() {
        title = findViewById(R.id.tv_reservation_title)
        date = findViewById(R.id.tv_reservation_date)
        count = findViewById(R.id.tv_reservation_count)
        amount = findViewById(R.id.tv_reservation_amount)
    }

    private fun initView() {
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
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}
