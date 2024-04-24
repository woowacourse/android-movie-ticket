package woowacourse.movie

import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import woowacourse.movie.TicketingActivity.Companion.EXTRA_MOVIE_ID
import woowacourse.movie.model.MovieData.findMovieById
import woowacourse.movie.model.Result
import java.lang.IllegalStateException

class SeatSelectionActivity : AppCompatActivity() {
    var positions = Positions.of(maxCount = 3)
    val button: Button by lazy { findViewById(R.id.btn_complete_reservation) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getLongExtra(EXTRA_MOVIE_ID, -1)
        val movie = findMovieById(id)
        button.isClickable = false
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
                        setTextColor(ContextCompat.getColor(this@SeatSelectionActivity, R.color.black))
                        val position = Position(rowIndex, columnIndex)

                        if (position in positions.value) {
                            setBackgroundColor(
                                ContextCompat.getColor(
                                    this@SeatSelectionActivity,
                                    R.color.yellow,
                                ),
                            )
                        } else {
                            setBackgroundColor(
                                ContextCompat.getColor(
                                    this@SeatSelectionActivity,
                                    R.color.white,
                                ),
                            )
                        }
                        setPadding(40 * resources.displayMetrics.density.toInt())
                        setOnClickListener {
                            if (position !in positions.value) {
                                runCatching {
                                    positions =
                                        Positions.of(
                                            3,
                                            positions.value + position,
                                        )
                                    setBackgroundColor(
                                        ContextCompat.getColor(
                                            this@SeatSelectionActivity,
                                            R.color.yellow,
                                        ),
                                    )
                                }.onFailure {
                                    Toast.makeText(
                                        this@SeatSelectionActivity,
                                        "추가 불가능",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            } else {
                                positions = Positions.of(3, positions.value - position)
                                setBackgroundColor(
                                    ContextCompat.getColor(
                                        this@SeatSelectionActivity,
                                        R.color.white,
                                    ),
                                )
                            }
                        }
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

data class Position(
    val row: Int,
    val column: Int,
)

class Positions private constructor(
    val value: List<Position>,
) {
    companion object {
        fun of(
            maxCount: Int,
            values: List<Position> = emptyList(),
        ): Positions {
            if (values.size > maxCount) throw IllegalStateException()
            return Positions(values)
        }
    }
}
