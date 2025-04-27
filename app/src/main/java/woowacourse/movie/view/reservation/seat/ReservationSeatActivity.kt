package woowacourse.movie.view.reservation.seat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.Position
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.dialog.DialogFactory
import woowacourse.movie.view.reservation.detail.ReservationActivity
import java.text.DecimalFormat

class ReservationSeatActivity : AppCompatActivity(), ReservationSeatContract.View {
    private val presenter: ReservationSeatContract.Present by lazy {
        ReservationSeatPresenter(this)
    }
    private lateinit var seat: TableLayout
    private val moviePriceTextView by lazy { findViewById<TextView>(R.id.reservation_movie_money) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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
        seat = findViewById<TableLayout>(R.id.tv_seat)
        presenter.fetchData(ticket)
    }

    private fun getAllSeatTextViews(): Sequence<TextView> {
        return seat
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
    }

    override fun setSeatTag() {
        getAllSeatTextViews().forEachIndexed { index, textView ->
            val row = index / 4
            val column = index % 4
            textView.tag = Position(row, column)
        }
    }

    override fun setSeatNumber() {
        getAllSeatTextViews().forEach { textView ->
            val position = textView.tag as Position
            textView.text = getSeatName(position)
            setSeatColor(textView, position)
        }
    }

    override fun showMovieName(movieName: String) {
        val movieTitleTextView = findViewById<TextView>(R.id.reservation_movie_title)
        movieTitleTextView.text = movieName
    }

    override fun showTicketMoney(moviePrice: Int) {
        val priceFormatter = DecimalFormat(PRICE_PATTERN)
        moviePriceTextView.text = getString(R.string.movie_money, priceFormatter.format(moviePrice))
    }

    private fun getSeatName(position: Position): String {
        val columnChar = 'A' + position.row
        return "$columnChar${position.column + 1}"
    }

    private fun setSeatColor(
        textView: TextView,
        position: Position,
    ) {
        val color =
            when (position.row) {
                0, 1 -> Color.MAGENTA
                2, 3 -> Color.BLUE
                else -> Color.GREEN
            }
        textView.setTextColor(color)
    }

    override fun handleInvalidTicket() {
        DialogFactory().showError(this) {
            ReservationActivity.returnToReserve(this)
        }
    }

    companion object {
        private const val KEY_TICKET = "ticket"
        private const val PRICE_PATTERN = "#,###"

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, ReservationSeatActivity::class.java).putExtra(
                KEY_TICKET,
                ticket,
            )
    }
}
