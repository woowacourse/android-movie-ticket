package woowacourse.movie.screeningmovie

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class AdvertiseUiModel(
    @DrawableRes val image: Int = R.drawable.img_advertisement,
) : ScreeningItem
