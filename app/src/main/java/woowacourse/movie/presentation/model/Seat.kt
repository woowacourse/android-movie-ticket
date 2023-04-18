package woowacourse.movie.presentation.model

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize
import woowacourse.movie.databinding.ItemMovieSeatBinding
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
        onClick: ItemMovieSeatBinding.() -> Unit = {},
    ): View =
        ItemMovieSeatBinding.inflate(LayoutInflater.from(context)).apply {
            val rowPosition = row.toDomain().value
            val seatColorResId = SeatClass.get(rowPosition).toPresentation().colorResId

            seatNumberTv.text = this@Seat.toString()
            seatNumberTv.setTextColor(ContextCompat.getColor(context, seatColorResId))
            seatNumberTv.isSelected = isChecked
            root.setOnClickListener { onClick(this) }
        }.root

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
