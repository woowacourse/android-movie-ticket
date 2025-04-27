package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.dialog.DialogFactory
import woowacourse.movie.view.reservation.detail.ReservationActivity
import java.time.format.DateTimeFormatter

class ReservationCompleteActivity : AppCompatActivity(), ReservationCompleteContract.View {
    private val presenter by lazy {
        ReservationCompletePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout_reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(KEY_TICKET, Ticket::class.java)
            } else {
                intent.getSerializableExtra(KEY_TICKET) as? Ticket
            }
        presenter.fetchData(ticket)
    }

    override fun handleInvalidTicket() {
        DialogFactory().showError(this) {
            ReservationActivity.returnToReserve(this)
        }
    }

    override fun showTicketInfo(ticket: Ticket) {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val dateTimeFormat = ticket.date.format(formatter)

        val movieTitleTextView = findViewById<TextView>(R.id.tv_movie_title)
        val movieCancelInfoTextView = findViewById<TextView>(R.id.tv_cancel_info)
        val movieDateTextView = findViewById<TextView>(R.id.tv_movie_date)
        val moviePersonnel = findViewById<TextView>(R.id.tv_movie_personnel)
        val movieTotalPrice = findViewById<TextView>(R.id.tv_movie_total_price)

        movieTitleTextView.text = ticket.title
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, Ticket.CANCEL_DEADLINE)
        movieDateTextView.text = dateTimeFormat
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, totalPrice(personnel = ticket.personnel))
    }

    private fun totalPrice(
        price: Int = Ticket.DEFAULT_PRICE,
        personnel: Int,
    ): String {
        val priceFormatter = DecimalFormat(PRICE_PATTERN)
        return priceFormatter.format(price * personnel).toString()
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d. HH:mm"
        private const val PRICE_PATTERN = "#,###"
        private const val KEY_TICKET = "ticket"

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, ReservationCompleteActivity::class.java).putExtra(
                KEY_TICKET,
                ticket,
            )
    }
}
