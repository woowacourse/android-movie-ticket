package woowacourse.movie.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import woowacourse.movie.R
import java.io.Serializable

data class SeatModel(
    val row: Int,
    val column: Int
) : Serializable {

    fun getView(
        context: Context,
        isSelected: Boolean,
        onClick: View.() -> Unit
    ): View {
        return LayoutInflater.from(context).inflate(R.layout.item_seat, null, false).apply {
            this.isSelected = isSelected
            updateBackgroundColor(this, context)
            setOnClickListener {
                onClick()
            }
            findViewById<TextView>(R.id.seat_view).apply {
                text = this@SeatModel.toString()
                setTextColor(context.getColor(SeatClassModel.getColorId(row)))
            }
        }
    }

    private fun updateBackgroundColor(seatView: View, context: Context) {
        when (seatView.isSelected) {
            true -> { seatView.setBackgroundColor(context.getColor(R.color.seat_selected_background)) }
            false -> { seatView.setBackgroundColor(context.getColor(R.color.seat_unselected_background)) }
        }
    }

    override fun toString(): String {
        return "${(row.convertToUpperLetter())}$column"
    }

    private fun Int.convertToUpperLetter(): Char = (this + 64).toChar()
}
