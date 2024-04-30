package woowacourse.movie.ui

import woowacourse.movie.domain.model.DateRange

data class ScreenPreviewUI(
    val id: Int,
    val moviePreviewUI: MoviePreviewUI,
    val dateRange: DateRange,
)
