package woowacourse.movie.view.mapper

import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.view.data.SeatsViewData
import woowacourse.movie.view.mapper.MovieSeatMapper.toView

object SeatsMapper {
    fun Seats.toView(): SeatsViewData {
        return SeatsViewData(
            value.map { it.toView() }
        )
    }
}
