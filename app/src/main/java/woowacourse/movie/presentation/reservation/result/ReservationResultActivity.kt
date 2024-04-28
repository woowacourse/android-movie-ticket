package woowacourse.movie.presentation.reservation.result

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket

class ReservationResultActivity : AppCompatActivity(), ReservationResultContract.View {
    private val presenter: ReservationResultContract.Presenter by lazy {
        ReservationResultPresenter(
            this,
            MovieDao(),
        )
    }
    private val titleTextView: TextView by lazy { findViewById(R.id.result_title_textview) }
    private val screenDataTextView: TextView by lazy { findViewById(R.id.result_screen_date_textview) }
    private val screenTimeTextView: TextView by lazy { findViewById(R.id.result_screen_time_textview) }
    private val countTextView: TextView by lazy { findViewById(R.id.result_count_textview) }
    private val priceTextView: TextView by lazy { findViewById(R.id.result_price_textview) }
    private val seatsTextView: TextView by lazy { findViewById(R.id.result_seat_textview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter.fetch(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun showMovieInformation(title: String) {
        titleTextView.text = title
    }

    override fun showReservationInformation(
        ticket: Ticket,
        seats: Seats,
    ) {
        priceTextView.text = getString(R.string.price_format, seats.totalPrice())
        countTextView.text = ticket.count.toString()
        screenDataTextView.text = ticket.screeningInfo.first.toString()
        screenTimeTextView.text = ticket.screeningInfo.second.toString()
        seatsTextView.text =
            seats.seats.joinToString(getString(R.string.seat_separator)) { " ${'A' + it.row}${it.col + 1}" }
    }
}
