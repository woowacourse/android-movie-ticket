package woowacourse.movie.view.reservationComplete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.view.Formatter.localDateToUI
import woowacourse.movie.view.Formatter.movieTimeToUI
import woowacourse.movie.view.Formatter.priceToUI
import woowacourse.movie.view.getSerializableExtraData
import woowacourse.movie.view.reservation.ReservationActivity
import java.time.LocalDate
import java.time.LocalTime

class ReservationCompleteActivity : AppCompatActivity() {
    private val movieTicket by lazy { intent.getSerializableExtraData<MovieTicket>(TICKET_DATA_KEY) }

    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_reservation_complete_title) }
    private val screeningDateTextView: TextView by lazy { findViewById(R.id.tv_reservation_complete_timestamp) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_reservation_complete_ticket_count) }
    private val ticketPriceTextView: TextView by lazy { findViewById(R.id.tv_reservation_complete_ticket_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMovieTicketInfo()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupMovieTicketInfo() {
        movieTitleTextView.text = movieTicket?.title
        screeningDateTextView.text =
            getString(
                R.string.reservation_ticket_timestamp,
                localDateToUI(movieTicket?.movieDate ?: LocalDate.now()),
                movieTimeToUI(movieTicket?.movieTime?.value ?: LocalTime.now().hour),
            )
        ticketCountTextView.text =
            resources.getString(R.string.reservation_complete_ticket_count, movieTicket?.count)
        ticketPriceTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_price,
                priceToUI(movieTicket?.price() ?: 13000),
            )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val TICKET_DATA_KEY = "movieTicket"

        fun getIntent(
            context: Context,
            movieTicket: MovieTicket,
        ): Intent =
            Intent(context, ReservationActivity::class.java).apply {
                putExtra(TICKET_DATA_KEY, movieTicket)
            }
    }
}
