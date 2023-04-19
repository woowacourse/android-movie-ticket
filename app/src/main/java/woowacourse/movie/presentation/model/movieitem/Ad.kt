package woowacourse.movie.presentation.model.movieitem

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class Ad(
    @DrawableRes val bannerResId: Int,
    val url: String,
) {
    companion object {
        fun provideDummy(): List<Ad> = List(10_000) {
            Ad(
                R.drawable.img_native_ad_banner,
                "https://woowacourse.github.io/"
            )
        }
    }
}
