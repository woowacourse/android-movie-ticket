package woowacourse.movie.view.seatSelection

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.presenter.seatSelection.SeatSelectionContracts
import woowacourse.movie.presenter.seatSelection.SeatSelectionPresenter
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.columnToUI
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.rowToUI

class SeatSelectionActivity :
    AppCompatActivity(),
    SeatSelectionContracts.View {
    private val presenter: SeatSelectionContracts.Presenter = SeatSelectionPresenter(this)
    private val seatsLayout: TableLayout by lazy { findViewById(R.id.tl_seat_selection) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seat_selection_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.initSeat()
    }

    override fun showSeats(seats: List<Seat>) {
        seatsLayout.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEachIndexed { colIndex, view ->
                        val key = "${rowToUI(rowIndex)}${columnToUI(colIndex)}"
                        view.setTag(R.id.seat_cell_key, key)
                        view.text = key
                        view.setOnClickListener {
                            view.isSelected = !view.isSelected
                        }
                    }
            }
    }
}
