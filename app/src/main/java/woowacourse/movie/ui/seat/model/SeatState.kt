package woowacourse.movie.ui.seat.model

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seats

class SeatState(
    val headcount: Headcount,
    val movieTitle: String,
    val selectedSeats: Seats,
)
