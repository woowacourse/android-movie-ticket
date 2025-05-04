package woowacourse.movie.domain.movies

sealed class MovieListItem {
    data class MovieItem(
        val movie: Movie,
    ) : MovieListItem()

    data object AdvertisementItem : MovieListItem()
}
