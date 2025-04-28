package woowacourse.movie.completedbooking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.parcelableCompat

class CompletedBookingActivity : AppCompatActivity(), CompletedBookingContract.View {
    private lateinit var completedBookingPresenter: CompletedBookingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_completed_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_completed_booking_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        completedBookingPresenter = CompletedBookingPresenter(this)

        val ticket: Ticket = intent.parcelableCompat(KEY_TICKET, Ticket::class.java)
        completedBookingPresenter.set(ticket)
    }

    override fun showCancelDeadLine(deadline: Int) {
        val movieCancelInfoTextView = findViewById<TextView>(R.id.ticket_cancel_info_Text)
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, deadline)
    }

    override fun showMovieTitle(title: String) {
        val movieTitleTextView = findViewById<TextView>(R.id.ticket_movie_title)
        movieTitleTextView.text = title
    }

    override fun showMovieDateTime(dateTime: String) {
        val movieDateTextView = findViewById<TextView>(R.id.ticket_movie_datetime)
        movieDateTextView.text = dateTime
    }

    override fun showPersonnel(
        personnel: Int,
        seats: String,
    ) {
        val moviePersonnel = findViewById<TextView>(R.id.ticket_movie_personnel)
        moviePersonnel.text = getString(R.string.moviePersonnel, personnel, seats)
    }

    override fun showTicketTotalPrice(price: String) {
        val movieTotalPrice = findViewById<TextView>(R.id.ticket_total_price)
        movieTotalPrice.text = getString(R.string.ticketTotalPrice, price)
    }

    companion object {
        private const val KEY_TICKET = "ticket"

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent {
            return Intent(context, CompletedBookingActivity::class.java).putExtra(KEY_TICKET, ticket)
        }
    }
}
