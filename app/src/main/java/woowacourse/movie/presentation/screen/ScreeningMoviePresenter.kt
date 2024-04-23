package woowacourse.movie.presentation.screen

import woowacourse.movie.model.Movie

class ScreeningMoviePresenter(private val view: ScreeningMovieContract.View) :
    ScreeningMovieContract.Presenter {
    override fun registerMovie(movie: Movie) {
        val movieId = movie.id
        view.startNextActivity(movieId)
    }
}
