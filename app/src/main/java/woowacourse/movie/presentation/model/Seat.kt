package woowacourse.movie.presentation.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import woowacourse.movie.databinding.ItemMovieSeatBinding
import woowacourse.movie.domain.model.seat.SeatClass
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toPresentation

class Seat(
    private val row: SeatRow,
    private val col: SeatColumn,
) {
    fun makeView(
        layoutInflater: LayoutInflater,
        parent: ViewGroup? = null,
        attachToParent: Boolean = false,
        onClick: ItemMovieSeatBinding.() -> Unit = {},
    ): View =
        ItemMovieSeatBinding.inflate(layoutInflater, parent, attachToParent).apply {
            val context = seatNumberTv.context
            val rowPosition = row.toDomain().value
            val seatColorResId = SeatClass.get(rowPosition).toPresentation().colorResId

            seatNumberTv.text = "$row$col"
            seatNumberTv.setTextColor(ContextCompat.getColor(context, seatColorResId))
            root.setOnClickListener { onClick(this) }
        }.root
}
