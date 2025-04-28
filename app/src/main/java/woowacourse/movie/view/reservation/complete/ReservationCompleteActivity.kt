package woowacourse.movie.view.reservation.complete

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

class ReservationCompleteActivity :
    AppCompatActivity(),
    ReservationCompleteContract.View {
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_reservation_complete_title) }
    private val reservationDateTimeTextView: TextView by lazy {
        findViewById(R.id.tv_reservation_complete_timestamp)
    }
    private val ticketCountTextView: TextView by lazy {
        findViewById(R.id.tv_reservation_complete_count_seats)
    }
    private val ticketPriceTextView: TextView by lazy { findViewById<TextView>(R.id.tv_reservation_complete_ticket_price) }
    private val presenter: ReservationCompletePresenter by lazy { ReservationCompletePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.fetchData {
            intent?.getParcelableExtraCompat<ReservationInfo>(Extras.ReservationInfoData.RESERVATION_KEY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showErrorDialog() {
    }

    override fun showReservationInfo(reservationInfo: ReservationInfo) {
        setupReservationInfo(reservationInfo)
    }

    private fun setupReservationInfo(reservationInfo: ReservationInfo) {
        movieTitleTextView.text = reservationInfo.title
        reservationDateTimeTextView.text =
            resources.getString(
                R.string.reservation_complete_date_time,
                ReservationUiFormatter.localDateToUI(reservationInfo.date),
                reservationInfo.time,
            )
        ticketCountTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_count,
                reservationInfo.seats.size,
                reservationInfo.seats.labels().joinToString(),
            )
        ticketPriceTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_price,
                ReservationUiFormatter.priceToUI(reservationInfo.price),
            )
    }
}
