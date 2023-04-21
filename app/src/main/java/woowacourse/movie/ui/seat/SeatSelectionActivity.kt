package woowacourse.movie.ui.seat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SelectedSeats
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.SeatClassModel
import woowacourse.movie.model.SeatModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.showToast

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var selectedSeats: SelectedSeats
    private val priceTextView by lazy { findViewById<TextView>(R.id.seat_price) }
    private val selectButton by lazy { findViewById<TextView>(R.id.seat_confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initPeopleCount()
        initSeatTable()
        initBottomField()
        initSelectButton()
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

    private fun initPeopleCount() {
        val count = PeopleCount(
            intent.getSerializableExtraCompat(MovieDetailActivity.KEY_PEOPLE_COUNT) ?: 1
        )
        selectedSeats = SelectedSeats(count)
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

    private fun initBottomField() {
        findViewById<TextView>(R.id.seat_movie_title).text =
            intent.getSerializableExtraCompat(MovieDetailActivity.KEY_TITLE) ?: ""
        updatePriceText(0)
    }

    private fun initSelectButton() {
        selectButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.seat_dialog_title))
                .setMessage(getString(R.string.seat_dialog_message))
                .setPositiveButton(getString(R.string.seat_dialog_submit_button)) { _, _ ->
                }
                .setNegativeButton(getString(R.string.seat_dialog_cancel_button)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun getSeatView(row: Int, column: Int): View {
        val seatView = LayoutInflater.from(this).inflate(R.layout.item_seat, null, false)
        val seat = SeatModel(row, column)
        seatView.setOnClickListener {
            clickSeat(seat.toDomain(), it)
        }
        seatView.findViewById<TextView>(R.id.seat_view).apply {
            text = seat.toString()
            setTextColor(getColor(SeatClassModel.getColorId(row)))
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

        updatePriceText(selectedSeats.getAllPrice())
        selectButton.isEnabled = selectedSeats.isSelectionDone()
    }

    private fun updatePriceText(price: Int) {
        priceTextView.text = getString(R.string.price_with_unit, price)
    }

    private fun canSelectMoreSeat(seatView: View) =
        !(seatView.isSelected && selectedSeats.isSelectionDone())

    companion object {
        const val ROW_SIZE = 5
        const val COLUMN_SIZE = 4
    }
}
