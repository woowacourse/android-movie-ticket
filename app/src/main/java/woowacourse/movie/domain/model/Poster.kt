package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Poster : Parcelable {
    data class Resource(
        val resId: Int,
    ) : Poster()

    data class Url(
        val url: String,
    ) : Poster()
}
