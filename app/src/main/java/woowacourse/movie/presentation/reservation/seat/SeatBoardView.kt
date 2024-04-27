package woowacourse.movie.presentation.reservation.seat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import woowacourse.movie.R
import woowacourse.movie.common.ui.dp
import woowacourse.movie.presentation.reservation.seat.model.SeatStateUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatUiModel


class SeatBoardView(
    context: Context,
    private val tableLayout: TableLayout,
    private val rowCount: Int,
    private val columnCount: Int,
) {
    private lateinit var seats: List<List<TextView>>

    init {
        initSeatsView(context)
    }

    private fun initSeatsView(context: Context) {
        val seatViews = mutableListOf<List<TextView>>()

        for (i in 0 until rowCount) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            val rowSeats = mutableListOf<TextView>()
            for (j in 0 until columnCount) {
                val textView = TextView(context).also { it.initSeatView() }
                tableRow.addView(textView)
                rowSeats.add(textView)
            }
            seatViews.add(rowSeats)
            tableLayout.addView(tableRow)
        }
        seats = seatViews
    }

    private fun TextView.initSeatView() {
        layoutParams = TableRow.LayoutParams(
            0,
            TableRow.LayoutParams.WRAP_CONTENT,
            1f
        )
        setPadding(0, 20.dp, 0, 20.dp)
        setGravity(Gravity.CENTER)
        background = GradientDrawable().apply {
            setCornerRadius(10f)
            setColor(
                ContextCompat.getColor(
                    context,
                    SeatStateUiModel.EMPTY.backGroundColor
                )
            )
            setStroke(3.dp, Color.BLACK)
        }
        val drawable = ContextCompat.getDrawable(context, SeatStateUiModel.SELECT.iconRes)
        drawable?.setBounds(0, 0, 28.dp, 28.dp)
        setCompoundDrawables(null, null, drawable, null)

        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
    }

    @SuppressLint("ResourceAsColor")
    fun updateSeat(seat: SeatUiModel) {
        val (x, y, state, grade) = seat
        val (backGroundColor, iconRes: Int) = state.backGroundColor to state.iconRes
        val seatView = seats[x][y]
        with(seatView) {
            background = GradientDrawable().apply {
                setCornerRadius(10f)
                setColor(ContextCompat.getColor(context, backGroundColor))
                setStroke(3, Color.BLACK)
            }
            val drawable = ContextCompat.getDrawable(context, iconRes)
            drawable?.setBounds(0, 0, 25.dp, 25.dp)
            setCompoundDrawables(null, null, drawable, null)
            if (state != SeatStateUiModel.BANNED) {
                val textColor = ContextCompat.getColor(context, grade.textColor)
                setTextColor(textColor)
                val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.roboto_bold)
                this.typeface = Typeface.create(typeface, Typeface.BOLD)
                text = seat.formatSeatPosition()
            }
        }
    }

    fun updateSeats(seats: List<SeatUiModel>) {
        seats.forEach(this::updateSeat)
    }

    fun setBoardClickListener(clickListener: SeatClickListener) {
        seats.applyOnChildren { seat, x, y ->
            seat.setOnClickListener { clickListener.onClick(x, y) }
        }
    }

    private fun List<List<TextView>>.applyOnChildren(action: (TextView, Int, Int) -> Unit) {
        seats.forEachIndexed { x, row ->
            row.forEachIndexed { y, textView ->
                action(textView, x, y)
            }
        }
    }

    fun interface SeatClickListener {
        fun onClick(
            x: Int,
            y: Int,
        )
    }
}
