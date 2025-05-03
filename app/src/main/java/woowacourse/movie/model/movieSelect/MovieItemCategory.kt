package woowacourse.movie.model.movieSelect

import woowacourse.movie.model.movieSelect.screening.Screening

sealed class MovieItemCategory {
    data class Movie(
        val screening: Screening,
    ) : MovieItemCategory()

    data class Ad(
        val advertisement: Advertisement,
    ) : MovieItemCategory()
}
