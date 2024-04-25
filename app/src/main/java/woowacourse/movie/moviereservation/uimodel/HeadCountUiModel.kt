package woowacourse.movie.moviereservation.uimodel

import woowacourse.movie.model.HeadCount

data class HeadCountUiModel(val count: String = HeadCount.MIN_COUNT.toString())
