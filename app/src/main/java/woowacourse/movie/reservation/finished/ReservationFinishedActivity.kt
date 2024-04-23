package woowacourse.movie.reservation.finished

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.home.ReservationHomeActivity
import woowacourse.movie.model.Movie
import java.text.DecimalFormat

class ReservationFinishedActivity : AppCompatActivity(), ReservationFinishedContract.View {
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_date) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_finished_number_of_tickets) }
    private val ticketPrice: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    private lateinit var presenter: ReservationFinishedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        onBackPressed(this)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val ticketCount = intent.getIntExtra(TICKET_COUNT, DEFAULT_TICKET_COUNT)

        presenter =
            ReservationFinishedPresenter(this, ticketCount).also {
                it.deliverMovieInformation(movieId)
                it.deliverReservationInformation()
            }
    }

    override fun showMovieInformation(movie: Movie) {
        title.text = movie.title
        screeningDate.text = movie.screeningDate
    }

    override fun showReservationHistory(
        ticketCount: Int,
        price: Int,
    ) {
        numberOfTickets.text = ticketCount.toString()
        ticketPrice.text = convertPriceFormat(price)
    }

    private fun onBackPressed(context: Context) {
        val callback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(context, ReservationHomeActivity::class.java)
                    startActivity(intent)
                }
            }

        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun convertPriceFormat(price: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price)
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET_COUNT = "ticketCount"
        private const val DEFAULT_MOVIE_ID = 0
        private const val DEFAULT_TICKET_COUNT = 0

        fun getIntent(
            context: Context,
            movieId: Int,
            ticketCount: Int,
        ): Intent {
            return Intent(context, ReservationFinishedActivity::class.java).also {
                it.putExtra(MOVIE_ID, movieId)
                it.putExtra(TICKET_COUNT, ticketCount)
            }
        }
    }
}
