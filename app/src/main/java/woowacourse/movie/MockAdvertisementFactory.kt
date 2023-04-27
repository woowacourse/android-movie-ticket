package woowacourse.movie

import woowacourse.movie.view.model.AdvertisementUiModel

object MockAdvertisementFactory {
    fun generateAdvertisement(): AdvertisementUiModel {
        return AdvertisementUiModel(R.drawable.advertisement, "https://www.naver.com")
    }
}
