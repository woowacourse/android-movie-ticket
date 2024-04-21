package woowacourse.movie.domain.model

import androidx.annotation.DrawableRes

interface Image<out T> {
    val imageSource: T
}

class DrawableImage(
    @DrawableRes override val imageSource: Int,
) : Image<Int>

class StringImage(override val imageSource: String) : Image<String>
