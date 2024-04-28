package woowacourse.movie.model

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position
import java.io.Serializable

class Reservation(
    val movieId: Int,
    val reservedDateTime: ScreeningDateTime,
    val positions: List<Position>,
    val price: Int
)
