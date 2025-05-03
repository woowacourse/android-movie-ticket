package woowacourse.movie.ui.booking.model

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import java.time.LocalDateTime

data class BookingState(
    val movie: Movie,
    val headcount: Headcount,
    val selectedDatePosition: Int,
    val selectedTimePosition: Int,
    val selectedDateTime: LocalDateTime,
)
