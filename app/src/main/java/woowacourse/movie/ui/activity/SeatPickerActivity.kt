package woowacourse.movie.ui.activity

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.ui.data.Column
import woowacourse.movie.ui.data.Row
import woowacourse.movie.ui.data.Seat
import woowacourse.movie.ui.getParcelable
import woowacourse.movie.ui.model.MovieTicketModel

class SeatPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        val seatViews = findViewById<TableLayout>(R.id.layout_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        setSeatViews(seatViews)

        val ticket = intent.getParcelable<MovieTicketModel>("ticket")
        val titleView = findViewById<TextView>(R.id.seat_picker_title)
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        titleView.text = ticket?.title
        priceView.text = getString(R.string.price, "0")
    }

    private fun setSeatViews(seatViews: List<TextView>) {
        val seats =
            Row.values().flatMap { row -> Column.values().map { column -> Seat(row, column) } }
        seatViews.zip(seats) { view, seat ->
            view.text = getString(R.string.seat, seat.row.name, seat.column.number)
            view.setTextColor(getColor(seat.row.color))
        }
    }
}
