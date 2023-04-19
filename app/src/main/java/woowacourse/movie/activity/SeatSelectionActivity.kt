package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.TableSize
import woowacourse.movie.domain.seat.MovieSeatRow
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.view.data.SeatTable
import woowacourse.movie.view.widget.SeatTableLayout

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        makeSeatTableLayout(
            makeSeatTable(SEAT_ROW_COUNT, SEAT_COLUMN_COUNT)
        )

        findViewById<Button>(R.id.seat_selection_reserve_button).setOnClickListener {
            onClickReserveButton()
        }
    }

    private fun makeSeatTable(row: Int, column: Int): SeatTable {
        return (0..row * column).map {
            val y = it / column
            val x = it % column
            Seat(MovieSeatRow(y), x)
        }.let {
            SeatTable(Seats(it), TableSize(row, column))
        }
    }

    private fun makeSeatTableLayout(seatTable: SeatTable) {
        SeatTableLayout(findViewById(R.id.seat_selection_table))
            .makeSeatTable(seatTable)
    }

    private fun onClickReserveButton() {
        AlertDialog.Builder(this).setTitle(getString(R.string.seat_selection_alert_title))
            .setMessage(getString(R.string.seat_selection_alert_message))
            .setPositiveButton(getString(R.string.seat_selection_alert_positive)) { _, _ ->
                onReserve()
            }.setNegativeButton(getString(R.string.seat_selection_alert_negative)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun onReserve() {
        val intent = Intent(this, ReservationResultActivity::class.java)
        // intent.putExtra("", null)
        startActivity(intent)
    }

    companion object {
        private const val SEAT_ROW_COUNT = 5
        private const val SEAT_COLUMN_COUNT = 4
    }
}
