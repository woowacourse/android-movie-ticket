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
import woowacourse.movie.model.ticket.MovieTicket
import woowacourse.movie.presenter.reservationComplete.ReservationCompleteContracts
import woowacourse.movie.presenter.reservationComplete.ReservationCompletePresenter
import woowacourse.movie.view.extension.getSerializableExtraData
import woowacourse.movie.view.mapper.Formatter.localDateToUI
import woowacourse.movie.view.mapper.Formatter.movieTimeToUI
import woowacourse.movie.view.mapper.Formatter.priceToUI
import java.time.LocalDate

class ReservationCompleteActivity :
    AppCompatActivity(),
    ReservationCompleteContracts.View {
    private val presenter: ReservationCompleteContracts.Presenter =
        ReservationCompletePresenter(this)

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

        presenter.updateTicketData(intent.getSerializableExtraData<MovieTicket>(TICKET_DATA_KEY))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun showTitle(title: String) {
        movieTitleTextView.text = title
    }

    override fun showTimestamp(
        date: LocalDate,
        time: Int,
    ) {
        screeningDateTextView.text =
            getString(
                R.string.reservation_ticket_timestamp,
                localDateToUI(date),
                movieTimeToUI(time),
            )
    }

    override fun showTicketCount(count: Int) {
        ticketCountTextView.text =
            resources.getString(R.string.reservation_complete_ticket_count, count)
    }

    override fun showPrice(price: Int) {
        ticketPriceTextView.text =
            resources.getString(
                R.string.reservation_complete_ticket_price,
                priceToUI(price),
            )
    }

    companion object {
        private const val TICKET_DATA_KEY = "movieTicket"

        fun getIntent(
            context: Context,
            movieTicket: MovieTicket,
        ): Intent =
            Intent(context, ReservationCompleteActivity::class.java).apply {
                putExtra(TICKET_DATA_KEY, movieTicket)
            }
    }
}
