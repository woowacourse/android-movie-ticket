package woowacourse.movie.activity.seatselection

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.ConfirmDialog
import woowacourse.movie.R
import woowacourse.movie.activity.booking.BookingActivity.Companion.KEY_TICKET
import woowacourse.movie.activity.bookingresult.BookingResultActivity
import woowacourse.movie.domain.Ticket

class SeatSelectionActivity :
    AppCompatActivity(),
    SeatSelectionContract.View {
    private lateinit var presenter: SeatSelectionContract.Presenter
    private lateinit var seats: Sequence<Sequence<TextView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        presenter = SeatSelectionPresenter()
        presenter.attachView(this)

        val ticket: Ticket =
            intent.getParcelableExtra(KEY_TICKET)
                ?: run {
                    Toast
                        .makeText(this, R.string.no_movie_data_error_message, Toast.LENGTH_SHORT)
                        .show()
                    finish()
                    return
                }

        showMovieInfo(ticket)

        val seatTable = findViewById<TableLayout>(R.id.seat_table_layout)
        setupSeats(seatTable)
        selectSeat(ticket)
        handleConfirmButton(ticket)
    }

    private fun selectSeat(ticket: Ticket) {
        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, textView ->
                textView.setOnClickListener {
                    val willSelect = !textView.isSelected
                    if (willSelect && presenter.calculateAudienceCount(true) > ticket.count) {
                        Toast.makeText(this, R.string.seat_select_error_message, Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    val isSelected = presenter.onSeatClicked(textView, ticket)
                    presenter.calculateMoney(rowIndex, isSelected)
                    presenter.handleConfirmButtonActivation(seats)
                }
            }
        }
    }

    private fun setupSeats(seatTable: TableLayout) {
        seats =
            seatTable.children
                .filterIsInstance<TableRow>()
                .map { row -> row.children.filterIsInstance<TextView>() }
    }

    private fun handleConfirmButton(ticket: Ticket) {
        findViewById<TextView>(R.id.confirm_button).setOnClickListener {
            presenter.onConfirmButtonClicked(seats, ticket)
        }
    }

    override fun showMovieInfo(ticket: Ticket) {
        findViewById<TextView>(R.id.title).text = ticket.title
        showMoney(INITIAL_TICKET_PRICE)
    }

    override fun showMoney(money: Int) {
        findViewById<TextView>(R.id.money).text =
            String.format(resources.getString(R.string.money), money)
    }

    override fun updateConfirmButtonState(hasSelection: Boolean) {
        val confirmButton = findViewById<TextView>(R.id.confirm_button)
        confirmButton.isEnabled = hasSelection
    }

    override fun moveToBookingResult(ticket: Ticket) {
        ConfirmDialog.show(this, ticket) {
            val intent =
                Intent(this, BookingResultActivity::class.java).apply {
                    putExtra(KEY_TICKET, ticket)
                }
            startActivity(intent)
        }
    }

    companion object {
        private const val INITIAL_TICKET_PRICE = 0
    }
}
