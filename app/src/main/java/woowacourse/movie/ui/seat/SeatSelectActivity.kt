package woowacourse.movie.ui.seat

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.ReservationState
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.reservation.MovieDetailActivity.Companion.KEY_RESERVATION
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class SeatSelectActivity : BackKeyActionBarActivity() {

    private val seats: TableLayout by lazy { findViewById(R.id.seats) }
    private val titleTextView: TextView by lazy { findViewById(R.id.reservation_title) }
    private val moneyTextView: TextView by lazy { findViewById(R.id.reservation_money) }

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_seat_select)

        val reservationState =
            intent.getParcelableExtraCompat<ReservationState>(KEY_RESERVATION) ?: return keyError(
                KEY_RESERVATION
            )

        titleTextView.text = reservationState.movieState.title

//
//        getAllSeatView().forEachIndexed { index, view ->
//            view.setOnClickListener {
//                val position = convertIndexToPosition(index)
//            }
//        }
    }

    private fun getAllSeatView(): List<TextView> {
        return seats
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()
    }

//    private fun convertIndexToPosition(index: Int): Position {
//        val row = ROW_SIZE - 1 - (index / ROW_SIZE)
//        val column = index % COLUMN_SIZE
//        Log.d("mendel", "index: $index , column: $column , row: $row")
//        return Position(column + 1, row + 1)
//    }
}
