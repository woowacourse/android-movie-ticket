package woowacourse.movie.activity.seatselection

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.activity.booking.BookingActivity.Companion.KEY_TICKET
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
        selectSeat()
    }

    private fun selectSeat() {
        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, textView ->
                textView.setOnClickListener {
                    val isSelected = presenter.onSeatClicked(textView)
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

    companion object {
        private const val INITIAL_TICKET_PRICE = 0
    }
}
