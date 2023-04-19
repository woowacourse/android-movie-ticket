package woowacourse.movie.view.mapper

import android.graphics.Color
import woowacourse.movie.domain.seat.SeatRank

object MovieColorMapper : ColorMapper<SeatRank> {
    override fun SeatRank.matchColor(): Int =
        when (this) {
            SeatRank.B -> Color.MAGENTA
            SeatRank.S -> Color.GREEN
            SeatRank.A -> Color.BLUE
        }
}
