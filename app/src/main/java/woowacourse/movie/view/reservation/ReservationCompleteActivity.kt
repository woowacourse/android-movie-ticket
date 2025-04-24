package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.view.Extras
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.getParcelableExtraCompat
import woowacourse.movie.view.movie.MoviesActivity

class ReservationCompleteActivity : AppCompatActivity() {
    private val reservationInfo by lazy { getReservationInfoData() }
    private val reservationUiFormatter: ReservationUiFormatter by lazy { ReservationUiFormatter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupReservationInfo()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getReservationInfoData(): ReservationInfo? = intent.getParcelableExtraCompat(Extras.ReservationInfoData.RESERVATION_KEY)

    private fun setupReservationInfo() {
        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_complete_title)
        movieTitleTextView.text = reservationInfo?.title

        val screeningDateTextView =
            findViewById<TextView>(R.id.tv_reservation_complete_timestamp)
        screeningDateTextView.text =
            resources.getString(
                R.string.reservation_complete_date_time,
                reservationUiFormatter.localDateToUI(reservationInfo?.date!!),
                reservationInfo?.time,
            )

        val ticketCountTextView = findViewById<TextView>(R.id.tv_reservation_complete_ticket_count)
        ticketCountTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_count,
                reservationInfo?.seats?.size,
                reservationInfo?.seats?.joinToString(),
            )

        val ticketPriceTextView = findViewById<TextView>(R.id.tv_reservation_complete_ticket_price)
        ticketPriceTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_price,
                reservationUiFormatter.priceToUI(reservationInfo?.price!!),
            )
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent =
            Intent(this, MoviesActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        startActivity(intent)
        return true
    }
}
