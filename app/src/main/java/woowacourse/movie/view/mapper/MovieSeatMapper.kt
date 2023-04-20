package woowacourse.movie.view.mapper

import woowacourse.movie.domain.seat.MovieSeatRow
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.view.data.SeatViewData
import woowacourse.movie.view.mapper.MovieColorMapper.matchColor

object MovieSeatMapper : Mapper<Seat, SeatViewData> {
    override fun Seat.toView(): SeatViewData {
        return SeatViewData(
            row.row, column, row.seatRankByRow().matchColor()
        )
    }

    override fun SeatViewData.toDomain(): Seat {
        return Seat(
            MovieSeatRow(row), column
        )
    }
}
