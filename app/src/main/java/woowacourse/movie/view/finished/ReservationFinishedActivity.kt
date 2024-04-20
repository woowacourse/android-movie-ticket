package woowacourse.movie.view.finished

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.finished.ReservationFinishedContract
import woowacourse.movie.presenter.finished.ReservationFinishedPresenter
import woowacourse.movie.view.detail.ReservationDetailActivity.Companion.DEFAULT_MOVIE_ID
import woowacourse.movie.view.detail.ReservationDetailActivity.Companion.TICKET
import woowacourse.movie.view.home.ReservationHomeActivity
import woowacourse.movie.view.home.ReservationHomeActivity.Companion.MOVIE_ID
import java.io.Serializable
import java.text.DecimalFormat

class ReservationFinishedActivity : AppCompatActivity(), ReservationFinishedContract.View {
    private val presenter: ReservationFinishedPresenter = ReservationFinishedPresenter(this)

    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_date) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_finished_number_of_tickets) }
    private val ticketPrice: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        onBackPressed(this)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val ticket = intent.intentSerializable(TICKET, Ticket::class.java) ?: Ticket()

        with(presenter) {
            loadMovie(movieId)
            loadTicket(ticket)
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

    private fun <T : Serializable> Intent.intentSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }

    private fun convertPriceFormat(price: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price)
    }
}
