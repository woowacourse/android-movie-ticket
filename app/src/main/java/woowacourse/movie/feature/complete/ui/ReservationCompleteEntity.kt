package woowacourse.movie.feature.complete.ui

import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.MovieContent

class ReservationCompleteEntity(
    val movieContent: MovieContent,
    val ticket: Ticket,
)
