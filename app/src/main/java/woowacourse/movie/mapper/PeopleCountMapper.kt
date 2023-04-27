package woowacourse.movie.mapper

import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.model.PeopleCountModel

fun PeopleCount.toModel(): PeopleCountModel = PeopleCountModel(
    count = count
)
