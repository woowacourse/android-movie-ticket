package woowacourse.movie.view.movie

import woowacourse.movie.common.DummyData
import woowacourse.movie.view.movie.adapter.MovieListItem
import woowacourse.movie.view.movie.model.AdUiModel
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.movie.model.toUiModel

class MovieListPresenter(
    private val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override fun loadMovieListScreen() {
        val movies = DummyData.movies.map { it.key.toUiModel() }
        val listWithAds = buildListWithAds(movies)
        view.showMovieList(listWithAds)
    }

    private fun buildListWithAds(movies: List<MovieUiModel>): List<MovieListItem> {
        val result = mutableListOf<MovieListItem>()
        movies.forEachIndexed { index, movie ->
            result.add(MovieListItem.MovieItem(movie))
            if ((index + 1) % AD_INTERVAL == 0) {
                result.add(MovieListItem.AdItem(AdUiModel()))
            }
        }
        return result
    }

    companion object {
        private const val AD_INTERVAL = 3
    }
}
