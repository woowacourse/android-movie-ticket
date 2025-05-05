package woowacourse.movie.view.reservation.seat

import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieseat.Position
import woowacourse.movie.domain.movieseat.Seats
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.view.dialog.DialogFactory
import woowacourse.movie.view.dialog.DialogInfo
import woowacourse.movie.view.reservation.result.ReservationCompleteActivity
import java.text.DecimalFormat

class ReservationSeatActivity : AppCompatActivity(), ReservationSeatContract.View {
    private val presenter: ReservationSeatContract.Present by lazy {
        ReservationSeatPresenter(this)
    }
    private lateinit var seat: TableLayout
    private val moviePriceTextView by lazy { findViewById<TextView>(R.id.reservation_movie_money) }
    private val movieSelectableButton by lazy { findViewById<TextView>(R.id.btn_confirm) }
    private var seatTextViews: MutableMap<Position, TextView> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket = intent.getSerializableExtraCompat<Ticket>(KEY_TICKET)

        seat = findViewById(R.id.tv_seat)

        fetchMovieOrShowError(ticket)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.onRestoreState(savedInstanceState)
    }

    private fun fetchMovieOrShowError(ticket: Ticket?) {
        if (ticket == null) {
            handleInvalidTicket()
        } else {
            presenter.fetchData(ticket)
        }
    }

    override fun setSeatTag() {
        seat
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .forEachIndexed { index, textView ->
                val row = index / 4
                val column = index % 4

                seatTextViews[Position(row, column)] = textView
            }
    }

    override fun setSeatInit() {
        seatTextViews.forEach { (position, textView) ->
            textView.text = getSeatName(position)
            setSeatColor(textView, position)
        }
    }

    override fun setSeatClickListener() {
        seatTextViews.forEach { (_, textView) ->
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
        val position = seatTextViews.entries.find { it.value == textView }?.key

        requireNotNull(position) { INVALID_TEXTVIEW }

        if (textView.isSelected) {
            presenter.deselectSeat(position)
        } else {
            presenter.selectSeat(position)
        }
    }

    override fun selectSeatView(position: Position) {
        val textView = seatTextViews.getValue(position)
        textView.setBackgroundColor(Color.YELLOW)
        textView.isSelected = true
    }

    override fun deselectSeatView(position: Position) {
        val textView = seatTextViews.getValue(position)
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

    override fun showReservationDialog(
        ticket: Ticket,
        seats: Seats,
    ) {
        DialogFactory().show(
            DialogInfo(
                this,
                R.string.reserve_confirm,
                R.string.askFor_reserve,
                R.string.complete,
                R.string.cancel,
            ),
        ) {
            navigateToReservationComplete(ticket, seats)
            finish()
        }
    }

    private fun navigateToReservationComplete(
        ticket: Ticket,
        seats: Seats,
    ) {
        val intent = ReservationCompleteActivity.newIntent(this@ReservationSeatActivity, ticket, seats)
        startActivity(intent)
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
            onBackPressedDispatcher.onBackPressed()
        }
    }

    companion object {
        private const val KEY_TICKET = "ticket"
        private const val PRICE_PATTERN = "#,###"
        private const val INVALID_TEXTVIEW = "선택한 textView의 position값이 할당되지 않았습니다."

        fun newIntent(
            context: Context,
            ticket: Ticket?,
        ): Intent =
            Intent(context, ReservationSeatActivity::class.java).putExtra(
                KEY_TICKET,
                ticket,
            )
    }
}
