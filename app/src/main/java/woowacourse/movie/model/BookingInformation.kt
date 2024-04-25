package woowacourse.movie.model

import woowacourse.movie.model.ticketing.Tickets2

class BookingInformation(
    val movieId: Long,
    val tickets: Tickets2,
    val count: Count,
)
