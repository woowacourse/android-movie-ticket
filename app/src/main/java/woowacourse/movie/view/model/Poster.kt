package woowacourse.movie.view.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

sealed class Poster : Parcelable {
    @Parcelize
    data class Resource(
        @DrawableRes val resId: Int,
    ) : Poster()

    @Parcelize
    data class Url(
        val url: String,
    ) : Poster()
}

fun ImageView.setPoster(poster: Poster) {
    when (poster) {
        is Poster.Resource -> this.setImageResource(poster.resId)
        is Poster.Url -> TODO("URL을 이용한 이미지 요청시 추가 필요")
    }
}
