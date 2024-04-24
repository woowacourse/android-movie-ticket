package woowacourse.movie

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.Gravity
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.model.MovieData.findMovieById
import woowacourse.movie.model.Result

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getLongExtra(EXTRA_MOVIE_ID, -1)
        val movie = findMovieById(id)
        when (movie) {
            is Result.Success -> {
                findViewById<TextView>(R.id.tv_movie_title).text = movie.data.title
            }
            is Result.Error -> {
            }
        }

        val seatInfo = SeatInfo(5, 4)
        initializeSeats(seatInfo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun initializeSeats(seatInfo: SeatInfo) {
        val tableLayout = findViewById<TableLayout>(R.id.tl_screens)
        tableLayout.isStretchAllColumns = true
        val rows = List(seatInfo.numOfRows) { TableRow(this) }
        rows.forEachIndexed { rowIndex, tableRow ->
            repeat(seatInfo.numOfColumns) { columnIndex ->
                val nameOfSeat =
                    TextView(this@SeatSelectionActivity).apply {
                        text = "${rowIndex + 1}${columnIndex + 1}"
                        setTextSize(COMPLEX_UNIT_SP, 22f)
                        gravity = Gravity.CENTER
                        setTextColor(Color.BLACK)
                        setPadding(60)
                    }
                tableRow.addView(nameOfSeat)
            }
            tableLayout.addView(tableRow)
        }
    }
}

data class SeatInfo(
    val numOfRows: Int,
    val numOfColumns: Int,
)
