package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount

data class HeadCountUiModel(val count: String = HeadCount.MIN_COUNT.toString())
