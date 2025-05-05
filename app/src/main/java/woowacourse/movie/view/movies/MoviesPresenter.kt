package woowacourse.movie.view.movies

import android.os.Bundle
import woowacourse.movie.data.DummyAdvertisement
import woowacourse.movie.data.DummyMovie
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListState

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    private var currentMovieListItems: List<MovieListItem> = emptyList()

    override fun loadData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val restoredState =
                savedInstanceState.getParcelable<MovieListState>(BUNDLE_KEY_MOVIE_LIST_ITEMS)
            if (restoredState != null) {
                currentMovieListItems = restoredState.items
                view.showMovies(currentMovieListItems)
                return
            }
        }
        val dummyMovies = DummyMovie.dummyMovie
        currentMovieListItems = buildListWithAds(dummyMovies)
        view.showMovies(currentMovieListItems)
    }

    fun getCurrentList(): List<MovieListItem> = currentMovieListItems

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

    companion object {
        const val BUNDLE_KEY_MOVIE_LIST_ITEMS = "movie_list_items"
    }
}
