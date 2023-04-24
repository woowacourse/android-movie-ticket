package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Advertisement
import woowacourse.movie.view.data.AdvertisementViewData
import woowacourse.movie.view.mapper.ImageMapper.toDomain
import woowacourse.movie.view.mapper.ImageMapper.toView

object AdvertisementMapper : Mapper<Advertisement, AdvertisementViewData> {
    override fun Advertisement.toView(): AdvertisementViewData {
        return AdvertisementViewData(
            banner.toView()
        )
    }

    override fun AdvertisementViewData.toDomain(): Advertisement {
        return Advertisement(
            banner.toDomain()
        )
    }
}
