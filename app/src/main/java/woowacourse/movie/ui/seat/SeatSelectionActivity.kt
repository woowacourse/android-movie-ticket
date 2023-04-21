package woowacourse.movie.ui.seat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SelectedSeats
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.SeatModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.showToast

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var selectedSeats: SelectedSeats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initPeopleCount()
        initSeatTable()
    }

    private fun initPeopleCount() {
        val count = PeopleCount(
            intent.getSerializableExtraCompat(MovieDetailActivity.KEY_PEOPLE_COUNT) ?: 1
        )
        selectedSeats = SelectedSeats(count)
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

    private fun initSeatTable() {
        val seatTable = findViewById<TableLayout>(R.id.seat_table_layout)
        for (row in 1..ROW_SIZE) {
            val tableRow = TableRow(this)
            for (column in 1..COLUMN_SIZE) {
                tableRow.addView(getSeatView(row, column))
            }
            seatTable.addView(tableRow)
        }
    }

    private fun getSeatView(row: Int, column: Int): View {
        val seatView = LayoutInflater.from(this).inflate(R.layout.item_seat, null, false)
        val seat = SeatModel(row, column)
        seatView.apply {
            findViewById<TextView>(R.id.seat_view).text = seat.toString()
            setOnClickListener {
                clickSeat(seat.toDomain(), this)
            }
        }
        return seatView
    }

    private fun clickSeat(seat: Seat, seatView: View) {
        if (!canSelectMoreSeat(seatView)) {
            showToast("이미 인원수만큼 좌석이 선택되었습니다")
            return
        }

        seatView.isSelected = !seatView.isSelected

        if (seatView.isSelected) {
            seatView.setBackgroundColor(getColor(R.color.seat_unselected_background))
            selectedSeats = selectedSeats.delete(seat)
        } else {
            seatView.setBackgroundColor(getColor(R.color.seat_selected_background))
            selectedSeats = selectedSeats.add(seat)
        }
    }

    private fun canSelectMoreSeat(seatView: View) =
        !(seatView.isSelected && selectedSeats.isSelectionDone())

    companion object {
        const val ROW_SIZE = 5
        const val COLUMN_SIZE = 4
    }
}
