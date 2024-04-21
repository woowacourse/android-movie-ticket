package woowacourse.movie.ui

import woowacourse.movie.domain.model.Image

data class MoviePreviewUI(
    val title: String,
    val runningTime: Int,
    val image: Image<Any>,
)
