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
import woowacourse.movie.view.dialog.DialogInfo
import woowacourse.movie.view.reservation.detail.ReservationActivity
import woowacourse.movie.view.reservation.result.ReservationCompleteActivity
import java.text.DecimalFormat

class ReservationSeatActivity : AppCompatActivity(), ReservationSeatContract.View {
    private val presenter: ReservationSeatContract.Present by lazy {
        ReservationSeatPresenter(this)
    }
    private lateinit var seat: TableLayout
    private val moviePriceTextView by lazy { findViewById<TextView>(R.id.reservation_movie_money) }
    private val movieSelectableButton by lazy { findViewById<TextView>(R.id.btn_confirm) }

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

    override fun setSeatInit() {
        getAllSeatTextViews().forEach { textView ->
            val position = textView.tag as Position
            textView.text = getSeatName(position)
            setSeatColor(textView, position)
        }
    }

    override fun setSeatClickListener() {
        getAllSeatTextViews().forEachIndexed { _, textView ->
            textView.setOnClickListener {
                toggleSeatSelection(textView)
            }
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

    override fun setReservationButton(onClickConfirm: () -> Unit) {
        movieSelectableButton.setOnClickListener {
            onClickConfirm()
        }
        movieSelectableButton.isEnabled = false
    }

    private fun toggleSeatSelection(textView: TextView) {
        val position = textView.tag as Position
        if (textView.isSelected) {
            presenter.deselectSeat(position)
        } else {
            presenter.selectSeat(position)
        }
    }

    override fun selectSeatView(position: Position) {
        val textView = findTextViewByPosition(position)
        textView.setBackgroundColor(Color.YELLOW)
        textView.isSelected = true
    }

    override fun deselectSeatView(position: Position) {
        val textView = findTextViewByPosition(position)
        textView.setBackgroundColor(Color.WHITE)
        textView.isSelected = false
    }

    override fun selectableButton() {
        movieSelectableButton.setBackgroundColor(getColor(R.color.purple_500))
        movieSelectableButton.isEnabled = true
    }

    override fun deSelectableButton() {
        movieSelectableButton.setBackgroundColor(getColor(R.color.gray))
        movieSelectableButton.isEnabled = false
    }

    override fun showReservationDialog(ticket: Ticket) {
        DialogFactory().show(
            DialogInfo(
                this,
                R.string.reserve_confirm,
                R.string.askFor_reserve,
                R.string.complete,
                R.string.cancel,
            ),
        ) {
            navigateToReservationComplete(ticket)
            finish()
        }
    }

    override fun navigateToReservationComplete(ticket: Ticket) {
        val intent = ReservationCompleteActivity.newIntent(this@ReservationSeatActivity, ticket)
        startActivity(intent)
    }

    private fun findTextViewByPosition(position: Position): TextView {
        return getAllSeatTextViews().first { it.tag == position }
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
