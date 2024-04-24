package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount

data class HeadCountUiModel(val count: String = HeadCount.DEFAULT_VALUE.toString())
