package woowacourse.movie.view.model

import androidx.annotation.DrawableRes

data class AdvertisementViewModel(
    @DrawableRes val image: Int,
    val url: String,
) : ViewModel
