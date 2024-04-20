package woowacourse.movie.domain

import android.widget.ListView
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presenter.MovieListPresenter

interface MovieListContract {
    interface View {
        fun showMovieInfo(info: ArrayList<Movie>)

        fun setOnListViewClickListener(info: ArrayList<Movie>)

        val presenter: MovieListPresenter

        val listView: ListView
    }

    interface Presenter {
        val movieList: ArrayList<Movie>

        fun setListViewInfo()

        fun setListViewClickListenerInfo()
    }
}
