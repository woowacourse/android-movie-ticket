package woowacourse.movie

import androidx.annotation.DrawableRes
import woowacourse.movie.movielist.ViewType

data class Ad(
    @DrawableRes
    val adImage: Int,
    val url: String,
    val viewType: ViewType = ViewType.AD,
) {
    companion object {
        val dummyAd = Ad(R.drawable.ad, "https://woowacourse.github.io/")
    }
}
