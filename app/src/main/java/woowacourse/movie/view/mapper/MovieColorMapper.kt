package woowacourse.movie.view.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.seat.SeatRank

object MovieColorMapper : ColorMapper<SeatRank> {
    override fun SeatRank.matchColor(): Int =
        when (this) {
            SeatRank.B -> R.color.b_rank
            SeatRank.S -> R.color.s_rank
            SeatRank.A -> R.color.a_rank
        }
}
