package woowacourse.movie.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.KeyIdentifiers
import woowacourse.movie.R
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation
import woowacourse.movie.ext.getSerializableCompat
import woowacourse.movie.main.MainActivity
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity(), ReservationResultContract.View {
    private val presenter = ReservationResultPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.setUpReservation(extractReservation())
        presenter.showReservation()
    }

    private fun extractReservation(): Reservation {
        return intent.getSerializableCompat<Reservation>(KeyIdentifiers.KEY_RESERVATION)
    }

    override fun bindReservation(reservation: Reservation) {
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)

        val formattedScreeningDate = formatting(reservation.reservedTime)

        title.text = reservation.movie.title
        screeningDate.text = formattedScreeningDate
    }

    override fun bindTicket(
        count: Int,
        seats: Set<Point>,
    ) {
        val ticketCount = findViewById<TextView>(R.id.tv_ticket)

        val seat =
            seats.joinToString { point ->
                getString(R.string.seat_point).format('A' + point.x, point.y + 1)
            }

        ticketCount.text = getString(R.string.formatted_ticket).format(count, seat)
    }

    override fun bindTotalPrice(price: Int) {
        val totalPrice = findViewById<TextView>(R.id.tv_total_price)

        totalPrice.text = getString(R.string.formatted_total_price).format(decimal.format(price))
    }

    private fun formatting(reservedDateTime: LocalDateTime): String {
        return reservedDateTime.format(formatter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
        private val decimal = DecimalFormat("#,###")

        fun newIntent(
            context: Context,
            reservation: Reservation,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(KeyIdentifiers.KEY_RESERVATION, reservation)
            }
    }
}
