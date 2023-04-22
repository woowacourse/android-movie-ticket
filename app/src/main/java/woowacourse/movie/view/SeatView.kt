package woowacourse.movie.view

import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import domain.Seat
import woowacourse.movie.R
import woowacourse.movie.setBackgroundColorId
import woowacourse.movie.view.mapper.SeatMapper
import woowacourse.movie.view.mapper.SeatRankMapper
import woowacourse.movie.view.model.SeatRowViewModel
import woowacourse.movie.view.model.SeatViewModel

class SeatView(val view: TextView, row: SeatRowViewModel, col: Int, onClick: (SeatView) -> Unit) {
    val seat: Seat
        get() = getSeat()

    init {
        view.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f
        )
        view.text = row.name + col
        view.textSize = 22f
        view.gravity = Gravity.CENTER
        view.isSelected = false
        setBackgroundColorId(R.color.white)
        initTextColor()
        view.setOnClickListener { onClick(this) }
    }

    @JvmName("callFromSeat")
    private fun getSeat(): Seat {
        val seatViewModel = getSeatViewModel()
        return SeatMapper.toDomain(seatViewModel)
    }

    private fun getSeatViewModel(): SeatViewModel {
        val row = view.text.substring(0, 1)
        val col = view.text.substring(1).toInt()
        return SeatViewModel(SeatRowViewModel.find(row), col)
    }

    private fun initTextColor() {
        val seatRankViewModel = SeatRankMapper.toView(seat.rank)
        view.setTextColor(
            ContextCompat.getColor(
                view.context, seatRankViewModel.color
            )
        )
    }

    fun setBackgroundColorId(color: Int) {
        view.setBackgroundColorId(color)
    }
}
