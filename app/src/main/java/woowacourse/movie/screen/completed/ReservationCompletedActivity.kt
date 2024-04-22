package woowacourse.movie.screen.completed

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Reservation
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)
    private val movieTitleTv by lazy {
        findViewById<TextView>(R.id.completed_movie_title)
    }
    private val reservationDateTv by lazy {
        findViewById<TextView>(R.id.completed_reservation_date)
    }
    private val quantityTv by lazy {
        findViewById<TextView>(R.id.completed_quantity)
    }
    private val priceTv by lazy {
        findViewById<TextView>(R.id.completed_price)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.onStart()
    }

    override fun readTicketData(): Reservation? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("reservation", Reservation::class.java)
        } else {
            intent.getSerializableExtra("reservation") as? Reservation
        }

    override fun initializeTicketDetails(reservation: Reservation) {
        val formattedDate = formatLocalDate(reservation)
        val formattedPrice = formatPrice(reservation)
        movieTitleTv.text = reservation.getTitle()
        reservationDateTv.text = formattedDate
        quantityTv.text = "일반 ${reservation.getQuantity()}명"
        priceTv.text = formattedPrice
    }

    private fun formatLocalDate(reservation: Reservation): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.getDefault())
        return reservation.getScreeningSchedule().run {
            format(formatter)
        }
    }

    private fun formatPrice(reservation: Reservation) = "${DecimalFormat(DECIMAL_FORMAT).format(reservation.price)}원 (현장 결제)"

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val DATE_FORMAT = "yyyy.M.d"
        private const val DECIMAL_FORMAT = "#,###"
    }
}
