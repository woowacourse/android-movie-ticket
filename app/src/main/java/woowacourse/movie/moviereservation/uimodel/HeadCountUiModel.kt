package woowacourse.movie.moviereservation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeadCountUiModel(val count: String) : Parcelable {
    constructor(count: Int) : this(count.toString())
}
