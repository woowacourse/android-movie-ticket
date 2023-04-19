package woowacourse.movie.model

import woowacourse.movie.domain.PeopleCount
import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicketModel(
    val title: String,
    val time: LocalDateTime,
    val peopleCount: PeopleCount,
) : Serializable
