package woowacourse.movie.presentation.model

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R
import woowacourse.movie.domain.model.seat.SeatClass
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toPresentation

@Parcelize
class Seat(
    val row: SeatRow,
    val col: SeatColumn,
) : Parcelable, Comparable<Seat> {
    fun makeView(
        context: Context,
        isChecked: Boolean = false,
        onClick: View.() -> Unit = {},
    ): View = LayoutInflater.from(context).inflate(R.layout.item_movie_seat, null).apply {
        val rowPosition = row.toDomain().value
        val seatColorResId = SeatClass.get(rowPosition).toPresentation().colorResId
        val seatNumberTextView = findViewById<TextView>(R.id.seat_number_tv)

        seatNumberTextView.text = this@Seat.toString()
        seatNumberTextView.setTextColor(ContextCompat.getColor(context, seatColorResId))
        seatNumberTextView.isSelected = isChecked
        setOnClickListener { onClick(this) }
    }

    override fun compareTo(other: Seat): Int {
        val rowCompare = row.compareTo(other.row)
        return if (rowCompare == 0) {
            col.compareTo(other.col)
        } else {
            rowCompare
        }
    }

    override fun toString(): String = "${row.value}${col.value}"
}
