package woowacourse.movie.model.main

import woowacourse.movie.R
import woowacourse.movie.advertisement.Advertisement

object AdvertisementMapper {
    fun Advertisement.toUiModel(): AdvertisementUiModel {
        return AdvertisementUiModel(
            id = this.id,
            link = this.link,
            image = R.drawable.woowa_advertisement,
        )
    }

    fun List<Advertisement>.toUiAdvertisements(): List<AdvertisementUiModel> =
        map { it.toUiModel() }
}
