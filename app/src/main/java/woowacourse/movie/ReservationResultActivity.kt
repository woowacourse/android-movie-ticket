package woowacourse.movie

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
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {
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

        initReservationInfo()
    }

    private fun initReservationInfo() {
        val reservation =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("reservation", Reservation::class.java)
            } else {
                intent.getParcelableExtra("reservation") as? Reservation
            }

        if (reservation == null) finish()

        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val ticketCount = findViewById<TextView>(R.id.tv_ticket_count)
        val totalPrice = findViewById<TextView>(R.id.tv_total_price)

        val formattedScreeningDate = formatting(reservation!!.reservedTime)

        title.text = reservation.title
        screeningDate.text = formattedScreeningDate
        ticketCount.text = TICKET_COUNT.format(reservation.count)
        totalPrice.text = TOTAL_PRICE.format(decimal.format(reservation.totalPrice()))
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
        private const val TICKET_COUNT = "%d명"
        private const val TOTAL_PRICE = "%s원"
    }
}
