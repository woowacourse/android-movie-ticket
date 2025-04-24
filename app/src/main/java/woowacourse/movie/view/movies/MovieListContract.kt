package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.movies.DefaultMovieModel
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.domain.model.movies.Movies

interface MovieListContract {
    interface View {
        fun showMovieList(movieList: MovieModel)

        fun moveToBookingComplete(movieIdx: Int)
    }

    interface Presenter {
        fun setMovies()

        fun onSelectMovie(movieIdx: Int)
    }

    interface MovieModel {
        operator fun get(index: Int): Movie

        fun getAll(): Movies

        fun size(): Int
    }

    companion object PresenterFactory {
        fun providePresenter(view: View): Presenter {
            val model: MovieModel = DefaultMovieModel()
            return MovieListPresenter(view, model)
        }
    }
}
