package woowacourse.movie.presentation.seats

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatPosition
import woowacourse.movie.presentation.bookingsummary.BookingSummaryActivity
import woowacourse.movie.ui.BaseActivity
import woowacourse.movie.ui.constant.IntentKeys
import woowacourse.movie.ui.util.TicketUiFormatter
import woowacourse.movie.ui.util.intentSerializable
import java.io.Serializable

class SeatsActivity : BaseActivity(), SeatsContract.View {
    override val layoutRes: Int
        get() = R.layout.activity_seats

    private val presenter: SeatsPresenter by lazy { SeatsPresenter(this) }
    private val seatsTable: TableLayout by lazy { findViewById(R.id.tablelayout_seats) }
    private val amountTextView: TextView by lazy { findViewById(R.id.textview_amount) }
    private val confirmTextView: TextView by lazy { findViewById(R.id.textview_confirm) }
    private lateinit var movieTicket: MovieTicket
    private var confirmDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(layoutRes)
        if (!fetchTicketFromIntent()) return
        presenter.loadSeats(movieTicket)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SEATS_KEY, presenter.selectedSeats.value as Serializable)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val selectedSeats = savedInstanceState.getSerializable(SEATS_KEY) as List<Seat>
        presenter.restoreSeats(selectedSeats)
    }

    override fun initSeats() {
        seatsTable.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, row ->
            row.children.filterIsInstance<TextView>().forEachIndexed { colIndex, view ->
                val seatPosition = SeatPosition(colIndex, rowIndex)
                view.tag = seatPosition
                view.setOnClickListener { presenter.selectSeat(seatPosition) }
            }
        }
    }

    override fun showMovieTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.textview_title)
        titleTextView.text = title
    }

    override fun showConfirmDialog() {
        if (confirmDialog == null) initConfirmDialog()
        confirmDialog?.show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAmount(amount: Int) {
        amountTextView.text = TicketUiFormatter.formatAmount(getString(R.string.amount_message), amount)
    }

    override fun updateSelectedSeat(seatPosition: SeatPosition, isSelected: Boolean) {
        val view = seatsTable.findViewWithTag<TextView>(seatPosition)
        view.setBackgroundResource(if(isSelected) R.color.selected_seat else R.color.white)
    }

    override fun updateConfirmButtonEnabled(canConfirm: Boolean) {
        if (canConfirm) {
            confirmTextView.setBackgroundColor(getColor(R.color.confirm_activate))
            confirmTextView.isClickable = true
            confirmTextView.setOnClickListener {
                showConfirmDialog()
            }
            return
        }
        confirmTextView.setBackgroundColor(getColor(R.color.confirm_deactivate))
        confirmTextView.isClickable = false
    }

    override fun navigateToSummary(ticket: MovieTicket) {
        val intent =
            Intent(this, BookingSummaryActivity::class.java).apply {
                putExtra(IntentKeys.TICKET, ticket)
            }
        startActivity(intent)
    }

    private fun fetchTicketFromIntent(): Boolean {
        val data = intent.intentSerializable(IntentKeys.TICKET, MovieTicket::class.java)
        if (data == null) {
            Toast.makeText(this, getString(R.string.ticket_intent_error), Toast.LENGTH_SHORT).show()
            finish()
            return false
        }
        movieTicket = data
        return true
    }

    private fun initConfirmDialog() {
        confirmDialog =
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton(getString(R.string.complete)) { _, _ -> presenter.publishMovieTicket() }
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .create()
    }

    companion object {
        private const val SEATS_KEY = "Seats"
    }
}
