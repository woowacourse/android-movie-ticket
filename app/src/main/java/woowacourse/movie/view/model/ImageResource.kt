package woowacourse.movie.view.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

sealed class ImageResource : Parcelable {
    @Parcelize
    data class Resource(
        @DrawableRes val resId: Int,
    ) : ImageResource()

    @Parcelize
    data class Url(
        val url: String,
    ) : ImageResource()
}

fun ImageView.setImageResource(resource: ImageResource) {
    when (resource) {
        is ImageResource.Resource -> this.setImageResource(resource.resId)
        is ImageResource.Url -> TODO("URL을 이용한 이미지 요청시 추가 필요")
    }
}
