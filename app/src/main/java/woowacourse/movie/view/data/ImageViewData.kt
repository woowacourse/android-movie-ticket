package woowacourse.movie.view.data

import androidx.annotation.DrawableRes
import java.io.Serializable

data class ImageViewData(
    @DrawableRes val resource: Int
) : Serializable
