package woowacourse.movie.ui

import woowacourse.movie.domain.model.DateRange

data class ScreenDetailUI(
    val id: Int,
    val movieDetailUI: MovieDetailUI,
    val dateRange: DateRange,
)
