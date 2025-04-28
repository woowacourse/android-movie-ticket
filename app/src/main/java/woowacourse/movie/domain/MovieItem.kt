package woowacourse.movie.domain

sealed class MovieItem {
    data class Movie(val movie: woowacourse.movie.domain.Movie) : MovieItem()

    data object Advertisement : MovieItem()
}
