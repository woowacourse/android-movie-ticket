package woowacourse.movie.view.movies

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieItem

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {
    override fun fetchData() {
        val movies: List<Movie> = Movie.dummy
        val movieItem = convertToMovieItems(movies)

        view.showMoviesScreen(movieItem)
    }

    private fun convertToMovieItems(movies: List<Movie>): List<MovieItem> {
        val items = mutableListOf<MovieItem>()
        movies.forEachIndexed { index, movie ->
            items.add(MovieItem.Movie(movie))
            if (index.isAdInsertionPosition()) {
                items.add(MovieItem.Advertisement)
            }
        }
        return items
    }

    private fun Int.isAdInsertionPosition(): Boolean {
        return (this + INDEX_OFFSET) % AD_INSERT_INTERVAL == 0
    }

    companion object {
        private const val AD_INSERT_INTERVAL = 3
        private const val INDEX_OFFSET = 1
    }
}
