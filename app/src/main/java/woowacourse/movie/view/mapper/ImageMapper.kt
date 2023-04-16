package woowacourse.movie.view.mapper

import woowacourse.movie.domain.Image
import woowacourse.movie.view.ImageView

object ImageMapper : Mapper<Image, ImageView> {
    override fun Image.toView(): ImageView {
        return ImageView(
            resource
        )
    }

    override fun ImageView.toDomain(): Image {
        return Image(
            resource
        )
    }
}
