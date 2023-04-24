package woowacourse.movie.view.mapper

import woowacourse.movie.R
import woowacourse.movie.domain.Image
import woowacourse.movie.view.data.ImageViewData

object ImageMapper : Mapper<Image, ImageViewData> {
    private const val ERROR_IMAGE_NOT_FOUND = "이미지 매핑 정보가 없습니다."

    private val images: List<Pair<Image, ImageViewData>> = listOf(
        Image(0) to ImageViewData(R.drawable.poster_harrypotter),
        Image(1) to ImageViewData(R.drawable.poster_harrypotter),
        Image(2) to ImageViewData(R.drawable.poster_harrypotter),
        Image(3) to ImageViewData(R.drawable.poster_harrypotter),
        Image(4) to ImageViewData(R.drawable.ad)
    )

    override fun Image.toView(): ImageViewData {
        return images.find { it.first == this }?.second ?: throw IllegalStateException(
            ERROR_IMAGE_NOT_FOUND
        )
    }

    override fun ImageViewData.toDomain(): Image {
        return images.find { it.second == this }?.first ?: throw IllegalStateException(
            ERROR_IMAGE_NOT_FOUND
        )
    }
}
