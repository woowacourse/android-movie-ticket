package woowacourse.movie.moviereservation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.HeadCount

@Parcelize
data class HeadCountUiModel(val count: String = HeadCount.MIN_COUNT.toString()) : Parcelable {
    constructor(count: Int) : this(count.toString())
}
