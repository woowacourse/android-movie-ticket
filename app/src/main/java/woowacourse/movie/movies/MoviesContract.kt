package woowacourse.movie.movies

import android.util.Log
import woowacourse.movie.domain.movies

interface MoviesContract {
    interface View {
        fun showMovies(uiModels: List<MovieUiModel>) {}

        fun navigateToBookingDetail(movieUiModel: MovieUiModel) {}
    }

    interface Presenter {
        fun loadMovies() {}

        fun onMovieSelected(movieUiModel: MovieUiModel) {}
    }
}

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun loadMovies() {
        val uiModels = movies.map { it.toMovieUiModel() }
        view.showMovies(uiModels)
    }

    override fun onMovieSelected(movieUiModel: MovieUiModel) {
        Log.d("MoviesPresenter", "onMovieSelected for ${movieUiModel.title}")
        view.navigateToBookingDetail(movieUiModel)
    }
}
