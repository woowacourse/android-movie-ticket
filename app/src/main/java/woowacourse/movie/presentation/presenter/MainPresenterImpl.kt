package woowacourse.movie.presentation.presenter

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movies
import woowacourse.movie.presentation.contract.MainContract

class MainPresenterImpl(private val view: MainContract.View) : MainContract.Presenter {
    private val movies = Movies()

    override fun movieList(): List<Movie> {
        movies.initMovieList()
        return movies.movies
    }

    override fun onReserveButtonClicked(movie: Movie) {
        view.moveToMovieDetail(movie)
    }
}
