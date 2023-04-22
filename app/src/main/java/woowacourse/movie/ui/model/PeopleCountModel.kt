package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.PeopleCount

fun PeopleCountModel.mapToPeopleCount() = PeopleCount(count)

fun PeopleCount.mapToPeopleCountModel() = PeopleCountModel(count)

@Parcelize
data class PeopleCountModel(val count: Int) : Parcelable
