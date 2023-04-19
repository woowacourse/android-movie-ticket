package woowacourse.movie.mapper

import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.model.MovieTicketModel

fun MovieTicket.toModel(): MovieTicketModel = MovieTicketModel(
    title = title,
    time = time,
    peopleCount = peopleCount
)

fun MovieTicketModel.toDomain(): MovieTicket = MovieTicket(
    title = title,
    time = time,
    peopleCount = peopleCount
)
