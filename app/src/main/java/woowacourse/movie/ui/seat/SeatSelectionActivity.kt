package woowacourse.movie.ui.seat

import android.content.Intent
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
import woowacourse.movie.mapper.toModel
import woowacourse.movie.model.MovieTicketModel
import woowacourse.movie.model.SeatClassModel
import woowacourse.movie.model.SeatModel
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import woowacourse.movie.ui.ticket.MovieTicketActivity
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.showToast
import java.time.LocalDateTime

class SeatSelectionActivity : AppCompatActivity() {

    private val priceTextView by lazy { findViewById<TextView>(R.id.seat_price) }
    private val selectButton by lazy { findViewById<TextView>(R.id.seat_confirm_button) }

    private var selectedSeats = SelectedSeats()
    private lateinit var movieTitle: String
    private lateinit var movieTime: LocalDateTime
    private var peopleCount = PeopleCount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initData()
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

    private fun initData() {
        movieTitle = intent.getSerializableExtraCompat(MovieDetailActivity.KEY_TITLE) ?: return failLoadingData()
        movieTime = intent.getSerializableExtraCompat(MovieDetailActivity.KEY_TIME) ?: return failLoadingData()
        peopleCount = PeopleCount(
            intent.getSerializableExtraCompat(MovieDetailActivity.KEY_PEOPLE_COUNT) ?: return failLoadingData()
        )
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
        findViewById<TextView>(R.id.seat_movie_title).text = movieTitle
        updatePriceText(0)
    }

    private fun initSelectButton() {
        selectButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.seat_dialog_title))
                .setMessage(getString(R.string.seat_dialog_message))
                .setPositiveButton(getString(R.string.seat_dialog_submit_button)) { _, _ ->
                    moveToTicketActivity()
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
        selectedSeats = if (seatView.isSelected) {
            seatView.setBackgroundColor(getColor(R.color.seat_unselected_background))
            selectedSeats.delete(seat)
        } else {
            seatView.setBackgroundColor(getColor(R.color.seat_selected_background))
            selectedSeats.add(seat)
        }
        updatePriceText(selectedSeats.getAllPrice(movieTime))
        selectButton.isEnabled = selectedSeats.isSelectionDone(peopleCount.count)
    }

    private fun updatePriceText(price: Int) {
        priceTextView.text = getString(R.string.price_with_unit, price)
    }

    private fun canSelectMoreSeat(seatView: View) =
        !(seatView.isSelected && selectedSeats.isSelectionDone(peopleCount.count))

    private fun moveToTicketActivity() {
        val ticket = MovieTicketModel(
            title = movieTitle,
            time = movieTime,
            peopleCount = peopleCount.toModel(),
            seats = selectedSeats.toModel()
        )

        val intent = Intent(this, MovieTicketActivity::class.java)
        intent.putExtra(KEY_TICKET, ticket)
        startActivity(intent)
    }

    private fun failLoadingData() {
        showToast(getString(R.string.error_loading))
        finish()
    }

    companion object {
        const val ROW_SIZE = 5
        const val COLUMN_SIZE = 4
        const val KEY_TICKET = "ticket"
    }
}
