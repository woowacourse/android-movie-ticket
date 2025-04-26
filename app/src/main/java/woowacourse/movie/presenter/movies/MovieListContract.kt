package woowacourse.movie.presenter.movies

import woowacourse.movie.data.MovieStore
import woowacourse.movie.view.movies.model.UiModel

interface MovieListContract {
    interface View {
        fun showMovieList(movieList: List<UiModel>)

        fun moveToBookingComplete(movieIdx: Int)
    }

    interface Presenter {
        fun loadUiData()

        fun onSelectMovie(movieIdx: Int)
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val model = MovieStore()
            return MovieListPresenter(view, model.getAll())
        }
    }
}
