package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.PeopleCount

fun mapToPeopleCount(peopleCount: PeopleCountModel): PeopleCount {
    return PeopleCount(
        peopleCount.count
    )
}

fun mapToPeopleCountModel(peopleCount: PeopleCount): PeopleCountModel {
    return PeopleCountModel(
        peopleCount.count
    )
}

@Parcelize
data class PeopleCountModel(val count: Int) : Parcelable
