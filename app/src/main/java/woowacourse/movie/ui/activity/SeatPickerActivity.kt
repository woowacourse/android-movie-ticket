package woowacourse.movie.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.ui.getParcelable
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.seat.Column
import woowacourse.movie.ui.seat.Rank
import woowacourse.movie.ui.seat.Row
import woowacourse.movie.ui.seat.Seat

class SeatPickerActivity : AppCompatActivity() {
    private var count = 0
    private val ranks = listOf(Rank.B, Rank.B, Rank.S, Rank.S, Rank.A)
    private val seats =
        Row.createRows(5).flatMapIndexed { rowIndex, row -> Column.createColumns(4).map { column -> Seat(row, column, ranks[rowIndex]) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val seatViews = findViewById<TableLayout>(R.id.layout_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        intent.getParcelable<MovieTicketModel>("ticket")?.let {
            setSeatViews(seatViews, it)
            findViewById<TextView>(R.id.seat_picker_title).text = it.title
        }
        findViewById<TextView>(R.id.seat_picker_price).text = getString(R.string.price, "0")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSeatViews(seatViews: List<TextView>, ticket: MovieTicketModel) {
        seatViews.zip(seats) { view, seat ->
            view.text = getString(R.string.seat, seat.row.value, seat.column.value)
            view.setTextColor(getColor(seat.rank.color))
            view.setOnClickListener {
                selectSeat(view, ticket)
                changeDoneButtonState(ticket)
            }
        }
    }

    private fun selectSeat(
        view: TextView,
        ticket: MovieTicketModel
    ) {
        if (view.isSelected) {
            count--
            view.setBackgroundColor(getColor(R.color.white))
            view.isSelected = false
        } else if (count < ticket.peopleCount.count) {
            count++
            view.setBackgroundColor(getColor(R.color.seat_selected))
            view.isSelected = true
        }
    }

    private fun changeDoneButtonState(ticket: MovieTicketModel) {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        if (count == ticket.peopleCount.count) {
            doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_on))
            doneButton.isClickable = true
        } else {
            doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_off))
            doneButton.isClickable = false
        }
    }
}
