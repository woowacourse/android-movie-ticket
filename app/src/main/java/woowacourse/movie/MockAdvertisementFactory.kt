package woowacourse.movie

import domain.Movies
import woowacourse.movie.view.model.AdvertisementViewModel

object MockAdvertisementFactory {
    fun generateAdvertisement(): AdvertisementViewModel {
        return AdvertisementViewModel(R.drawable.advertisement, "https://www.naver.com")
    }
}
