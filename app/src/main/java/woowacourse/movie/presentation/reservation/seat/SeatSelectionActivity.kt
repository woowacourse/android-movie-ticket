package woowacourse.movie.presentation.reservation.seat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.common.ui.dp


class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        val tableLayout = findViewById<TableLayout>(R.id.tl_seat_selection)
        val seatTableView = SeatTableView(
            context = this,
            tableLayout = tableLayout,
            rowCount = 6,
            columnCount = 5
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class SeatTableView(
    context: Context,
    private val tableLayout: TableLayout,
    private val rowCount: Int,
    private val columnCount: Int
) {
    private lateinit var seats: List<List<TextView>>

    init {
        createSeats(context)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    private fun createSeats(context: Context) {
        val seatViews = mutableListOf<List<TextView>>()

        for (i in 0 until rowCount) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            val rowSeats = mutableListOf<TextView>()
            for (j in 0 until columnCount) {
                val textView = TextView(context).apply {
                    layoutParams = TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                    setPadding(0, 20.dp, 0, 20.dp)
                    setTextColor(ContextCompat.getColor(context, R.color.blue_700))
                    setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    background = GradientDrawable().apply {
                        setCornerRadius(10f)
                        setColor(Color.LTGRAY)
                        setStroke(3.dp, Color.BLACK)
                    }
                    setOnClickListener {
                        background = GradientDrawable().apply {
                            setCornerRadius(10f)
                            setColor(Color.BLACK)
                            setStroke(3, Color.BLACK)
                        }
                    }
                    setGravity(Gravity.CENTER);
                    textSize = 16f
                    text = "${'A' + i}${j + 1}"
                }
                tableRow.addView(textView)
                rowSeats.add(textView)
            }
            seatViews.add(rowSeats)
            tableLayout.addView(tableRow)
        }

        seats = seatViews
    }

    @SuppressLint("ResourceAsColor")
    fun updateSeat(seat: SeatUiModel) {
        val (x, y, state, seatClass) = seat
        val textColor = seatClass.textColor
        val (backGroundColor, iconRes: Int?) = state.backGroundColor to state.iconRes
        val seat = seats[x][y]
        seat.setTextColor(textColor)
        seat.setBackgroundColor(backGroundColor)
        seat.background = ContextCompat.getDrawable(seat.context, iconRes);
    }

    fun updateSeats(seats: List<SeatUiModel>) {
        seats.forEach(this::updateSeat)
    }

    fun disableClickListener() {
        seats.applyOnChildren { seat ->
            seat.setOnClickListener(null)
        }
    }

    fun setBoardClickListener(clickListener: SeatClickListener) {
        seats.applyOnChildren { seat, x, y ->
            seat.setOnClickListener { clickListener.onClick(x + 1, y + 1) }
        }
    }

    private fun List<List<TextView>>.applyOnChildren(action: (TextView, Int, Int) -> Unit) {
        seats.forEachIndexed { x, row ->
            row.forEachIndexed { y, textView ->
                action(textView, x, y)
            }
        }
    }

    private fun List<List<TextView>>.applyOnChildren(action: (TextView) -> Unit) {
        seats.forEach { row ->
            row.forEach { textView ->
                action(textView)
            }
        }
    }

    fun interface SeatClickListener {
        fun onClick(
            x: Int,
            y: Int,
        )
    }

    companion object {
        private const val INITIAL_RESOURCE = 0
    }
}

data class SeatUiModel(
    val x: Int,
    val y: Int,
    val state: SeatState,
    val seatClass: SeatClass,
)

enum class SeatClass(@ColorRes val textColor: Int) {
    B(R.color.purple_500),
    A(R.color.green_300),
    S(R.color.blue_700),
}

private const val EMPTY_ICON = 0

enum class SeatState(
    @ColorRes val backGroundColor: Int,
    @DrawableRes val iconRes: Int = EMPTY_ICON
) {
    EMPTY(R.color.white, R.drawable.img_movie_poster),
    SELECT(R.color.white),
    RESERVED(R.color.white);

}