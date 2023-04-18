package woowacourse.movie.ui.model

import woowacourse.movie.domain.PeopleCount
import java.io.Serializable

fun mapToPeopleCountModel(peopleCount: PeopleCount): PeopleCountModel {
    return PeopleCountModel(
        peopleCount.count
    )
}

data class PeopleCountModel(val count: Int) : Serializable
