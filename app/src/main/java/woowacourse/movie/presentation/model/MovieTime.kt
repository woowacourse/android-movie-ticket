package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTime(val hour: Int, val min: Int = DEFAULT_MIN) : Parcelable {
    companion object {
        private const val DEFAULT_MIN = 0
    }
}
