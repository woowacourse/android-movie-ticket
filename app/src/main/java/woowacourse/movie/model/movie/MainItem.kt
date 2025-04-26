package woowacourse.movie.model.movie

import woowacourse.movie.model.movie.screening.Screening

sealed class MainItem {
    data class Movie(
        val screening: Screening,
    ) : MainItem()

    data class Advertisement(
        val ad: woowacourse.movie.model.movie.Advertisement,
    ) : MainItem()
}
