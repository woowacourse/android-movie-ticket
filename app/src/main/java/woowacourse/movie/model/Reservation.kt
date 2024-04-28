package woowacourse.movie.model

import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position

class Reservation(
    val movieId: Int,
    val reservedDateTime: ScreeningDateTime,
    val positions: List<Position>,
    val price: Int,
)
