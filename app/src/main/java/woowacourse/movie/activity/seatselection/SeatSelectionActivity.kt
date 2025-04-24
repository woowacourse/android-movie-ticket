package woowacourse.movie.activity.seatselection

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R

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

        val seatTable = findViewById<TableLayout>(R.id.seat_table_layout)
        setupSeats(seatTable)
        selectSeat()
    }

    private fun selectSeat() {
        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, textView ->
                textView.setOnClickListener { presenter.onSeatClicked(textView) }
            }
        }
    }

    private fun setupSeats(seatTable: TableLayout) {
        seats =
            seatTable.children
                .filterIsInstance<TableRow>()
                .map { row -> row.children.filterIsInstance<TextView>() }
    }

    override fun showSelectedSeat(seat: TextView) {
        seat.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow_400))
    }
}
