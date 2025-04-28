package woowacourse.movie.view.movies

import woowacourse.movie.data.DummyAdvertisement
import woowacourse.movie.data.DummyMovie
import woowacourse.movie.domain.model.Movie

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun loadData() {
        view.showMovies(buildListWithAds(DummyMovie.dummyMovie))
    }

    private fun buildListWithAds(movies: List<Movie>): List<MovieListItem> {
        val result = mutableListOf<MovieListItem>()
        movies.forEachIndexed { index, movie ->
            result.add(MovieListItem.MovieItem(movie))
            if ((index + 1) % 3 == 0) {
                result.add(MovieListItem.AdItem(DummyAdvertisement.advertisement))
            }
        }
        return result
    }
}
