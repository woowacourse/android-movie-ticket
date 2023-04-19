package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Image
import woowacourse.movie.view.data.ImageViewData

object ImageMapper : Mapper<Image, ImageViewData> {
    override fun Image.toView(): ImageViewData {
        return ImageViewData(
            resource
        )
    }

    override fun ImageViewData.toDomain(): Image {
        return Image(
            resource
        )
    }
}
