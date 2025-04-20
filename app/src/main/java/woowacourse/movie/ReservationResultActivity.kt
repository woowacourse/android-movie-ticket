package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Reservation
import woowacourse.movie.factory.CustomDialogFactory
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {
    private val customDialogFactory = CustomDialogFactory()

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

        initReservationResult()
    }

    private fun initReservationResult() {
        val reservation =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(KEY_RESERVATION, Reservation::class.java)
            } else {
                intent.getParcelableExtra(KEY_RESERVATION) as? Reservation
            }

        if (reservation == null) {
            showReservationError()
        } else {
            initReservation(reservation)
        }
    }

    private fun showReservationError() {
        customDialogFactory.emptyValueDialog(
            this,
            getString(R.string.error_reservation_title),
            getString(R.string.error_reservation_message),
            ::finish,
        ).show()
    }

    private fun initReservation(reservation: Reservation) {
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val ticketCount = findViewById<TextView>(R.id.tv_ticket_count)
        val totalPrice = findViewById<TextView>(R.id.tv_total_price)

        val screeningDateView = screeningDate(reservation.reservedTime)

        title.text = reservation.title
        screeningDate.text = screeningDateView
        ticketCount.text = getString(R.string.formatted_ticket_count, reservation.count)
        totalPrice.text = wonFormat(this).format(reservation.totalPrice())
    }

    private fun screeningDate(reservedDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_time_format))
        return formatter.format(reservedDateTime)
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
        private fun wonFormat(context: Context) = DecimalFormat(context.getString(R.string.won_format))

        const val KEY_RESERVATION = "reservation"
    }
}
