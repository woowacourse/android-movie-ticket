package woowacourse.movie.domain.model

import java.io.Serializable

class BookedTicket(
    val movieName: String,
    val peopleCount: PeopleCount,
    val time: String,
) : Serializable
