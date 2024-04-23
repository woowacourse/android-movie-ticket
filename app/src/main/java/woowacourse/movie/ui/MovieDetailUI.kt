package woowacourse.movie.ui

import woowacourse.movie.domain.model.Image

data class MovieDetailUI(
    val title: String,
    val runningTime: Int,
    val description: String,
    val image: Image<Any>,
)
