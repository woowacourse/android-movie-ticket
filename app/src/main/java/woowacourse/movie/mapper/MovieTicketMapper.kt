package woowacourse.movie.mapper

import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.model.MovieTicketModel

fun MovieTicketModel.toDomain(): MovieTicket =
    MovieTicket(
        title = title,
        time = time,
        peopleCount = peopleCount.toDomain(),
    )
