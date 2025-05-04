package woowacourse.movie.domain.model

sealed class MovieListItem {
    data class MovieItem(
        val movie: Movie,
    ) : MovieListItem()

    data class AdItem(
        val advertisement: Advertisement,
    ) : MovieListItem()
}
