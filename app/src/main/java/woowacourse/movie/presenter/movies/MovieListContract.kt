package woowacourse.movie.presenter.movies

import woowacourse.movie.data.MovieStore
import woowacourse.movie.view.movies.model.UiModel

interface MovieListContract {
    interface View {
        fun showMovieList(movieList: List<UiModel>)
    }

    interface Presenter {
        fun loadUiData()
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val model = MovieStore()
            return MovieListPresenter(view, model.getAll())
        }
    }
}
