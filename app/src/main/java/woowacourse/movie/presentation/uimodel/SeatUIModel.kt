package woowacourse.movie.presentation.uimodel

import woowacourse.movie.domain.model.Seat

data class SeatUIModel(
    val seat: Seat,
    var isSelected: Boolean = false,
)
