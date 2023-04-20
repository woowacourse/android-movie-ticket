package woowacourse.movie.view.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.Image
import woowacourse.movie.view.data.ImageViewData

object ImageMapper : Mapper<Image, ImageViewData> {
    private val images: List<Pair<Image, ImageViewData>> =
        listOf(Image(0) to ImageViewData(R.drawable.poster_harrypotter))

    override fun Image.toView(): ImageViewData {
        return images.find { it.first == this }?.second ?: throw IllegalStateException()
    }

    override fun ImageViewData.toDomain(): Image {
        return images.find { it.second == this }?.first ?: throw IllegalStateException()
    }
}
