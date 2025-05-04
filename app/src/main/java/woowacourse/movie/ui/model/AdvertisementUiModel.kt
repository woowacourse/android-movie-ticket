package woowacourse.movie.ui.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.advertisement.Advertisement

data class AdvertisementUiModel(
    @DrawableRes val content: Int,
) : MovieItem

fun Advertisement.toAdvertisementUiModel() = AdvertisementUiModel(content.toInt())
