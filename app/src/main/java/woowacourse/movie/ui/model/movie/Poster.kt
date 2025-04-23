package woowacourse.movie.ui.model.movie

import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Poster : Parcelable {
    data class Resource(
        @DrawableRes val resourceId: Int,
    ) : Poster()

    data class Url(val url: String) : Poster()
}

fun ImageView.setPosterImage(poster: Poster) {
    when (poster) {
        is Poster.Resource -> this.setImageResource(poster.resourceId)
        is Poster.Url -> { /* 이미지 로드 */ }
    }
}
