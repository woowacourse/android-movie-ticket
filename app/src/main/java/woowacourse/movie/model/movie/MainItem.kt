package woowacourse.movie.model.movie

import woowacourse.movie.model.movie.screening.Screening

sealed class MainItem {
    data class MovieItem(
        val screening: Screening,
    ) : MainItem()

    data class AdItem(
        val ad: Advertisement,
    ) : MainItem()
}
