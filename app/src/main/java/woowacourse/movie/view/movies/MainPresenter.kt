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
            if ((index + 1) % 3 == 0) {
                items.add(MovieItem.Advertisement)
            }
        }
        return items
    }
}
