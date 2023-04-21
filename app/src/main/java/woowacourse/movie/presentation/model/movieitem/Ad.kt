package woowacourse.movie.presentation.model.movieitem

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class Ad(
    @DrawableRes val bannerResId: Int,
    val url: String,
) : ListItem {
    companion object {
        fun provideDummy(): List<Ad> = listOf(
            Ad(
                R.drawable.img_sample_native_ad_banner1,
                "https://woowacourse.github.io/"
            ),
            Ad(
                R.drawable.img_sample_native_ad_banner2,
                "https://www.baemin.com/"
            ),
        )
    }

    override fun isAd(): Boolean = true
}
