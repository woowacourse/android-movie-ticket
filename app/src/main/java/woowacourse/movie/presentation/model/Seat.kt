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
) : Parcelable {
    fun makeView(
        context: Context,
        isChecked: Boolean = false,
        onClick: ItemMovieSeatBinding.() -> Unit = {},
    ): View =
        ItemMovieSeatBinding.inflate(LayoutInflater.from(context)).apply {
            val rowPosition = row.toDomain().value
            val seatColorResId = SeatClass.get(rowPosition).toPresentation().colorResId

            seatNumberTv.text = "${row.value}${col.value}"
            seatNumberTv.setTextColor(ContextCompat.getColor(context, seatColorResId))
            seatNumberTv.isSelected = isChecked
            root.setOnClickListener { onClick(this) }
        }.root
}
