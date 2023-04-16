package woowacourse.movie.ui.dto

import java.io.Serializable

fun mapToUIPeopleCount(peopleCount: woowacourse.movie.domain.PeopleCount): PeopleCount {
    return PeopleCount(
        peopleCount.count
    )
}

data class PeopleCount(val count: Int) : Serializable
