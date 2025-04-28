package woowacourse.movie.view.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.common.parcelableCompat
import woowacourse.movie.common.parcelableExtraCompat
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.result.ReservationResultActivity
import woowacourse.movie.view.seat.model.SeatUiModel

class SeatSelectActivity :
    AppCompatActivity(),
    SeatSelectContract.View {
    private lateinit var presenter: SeatSelectPresenter

    private val seatTable: TableLayout by lazy { findViewById(R.id.seat_table) }
    private val confirmButton: Button by lazy { findViewById(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

        val ticket =
            intent.parcelableExtraCompat(EXTRA_TICKET, TicketUiModel::class.java)
                ?: finish().run { return }
        presenter = SeatSelectPresenter(this, ticket)

        initSeatTable()
        initConfirmButton()
        presenter.loadSeatSelectScreen()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(EXTRA_TICKET, presenter.ticket)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredTicket =
            savedInstanceState.parcelableCompat(EXTRA_TICKET, TicketUiModel::class.java)
        restoredTicket?.let { restored ->
            presenter.restoreTicket(restored)
        }
    }

    override fun showMovieInfo(movie: MovieUiModel) {
        findViewById<TextView>(R.id.movie_title).text = movie.title
    }

    override fun showTotalPrice(price: Int) {
        findViewById<TextView>(R.id.total_price).text = getString(R.string.total_price, price)
    }

    override fun showConfirmAlertDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(R.string.confirm_reservation)
            .setMessage(R.string.confirm_reservation_message)
            .setPositiveButton(R.string.complete_reservation) { _, _ -> presenter.completeReservation() }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    override fun updateSeatSelectionState(
        seat: SeatUiModel,
        isSelected: Boolean,
    ) {
        val seatTextView: TextView = seatTable.findViewWithTag(seat)
        seatTextView.setBackgroundResource(if (isSelected) R.color.yellow else R.color.white)
    }

    override fun updateConfirmButtonState(isEnabled: Boolean) {
        confirmButton.isEnabled = isEnabled
    }

    override fun navigateToCompleteScreen(ticket: TicketUiModel) {
        val intent = ReservationResultActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initSeatTable() {
        seatTable.children.filterIsInstance<TableRow>().forEachIndexed { rowIdx, row ->
            row.children.filterIsInstance<TextView>().forEachIndexed { colIdx, seatTextView ->
                val seat = SeatUiModel(rowIdx, colIdx)
                seatTextView.apply {
                    tag = seat
                    text = seat.toString()
                    setTextColor(getColor(seat.colorResId))
                    setOnClickListener { presenter.onClickSeat(seat) }
                }
            }
        }
    }

    private fun initConfirmButton() {
        confirmButton.setOnClickListener { presenter.onClickConfirmButton() }
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent =
            Intent(context, SeatSelectActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
    }
}
