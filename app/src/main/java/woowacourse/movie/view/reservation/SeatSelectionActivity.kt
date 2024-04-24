package woowacourse.movie.view.reservation

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.db.SeatsDao
import woowacourse.movie.model.Grade
import woowacourse.movie.model.Seat
import woowacourse.movie.presenter.reservation.SeatSelectionContract
import woowacourse.movie.presenter.reservation.SeatSelectionPresenter

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val presenter: SeatSelectionPresenter = SeatSelectionPresenter(this, SeatsDao())

    private val seatTableLayout: TableLayout by lazy { findViewById(R.id.table_layout_seat_selection) }
    private lateinit var seats: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        seats =
            seatTableLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<Button>().toList()
        presenter.loadSeatNumber()
    }

    override fun showSeatNumber(
        index: Int,
        seat: Seat,
    ) {
        seats[index].apply {
            text = getString(R.string.select_seat_number).format(seat.row, seat.column)
            setTextColor(setUpSeatColorByGrade(seat.grade))
        }
    }

    override fun setUpSeatColorByGrade(grade: Grade): Int {
        return when (grade) {
            Grade.B -> getColor(R.color.purple_500)
            Grade.S -> getColor(R.color.teal_700)
            Grade.A -> getColor(R.color.blue)
        }
    }
}
