package woowacourse.movie.view.mapper

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.view.data.SeatViewData
import woowacourse.movie.view.mapper.MovieColorMapper.matchColor

object SeatMapper {
    fun Seat.toView(): SeatViewData {
        return SeatViewData(
            row.row, column, row.seatRankByRow().matchColor()
        )
    }
}
