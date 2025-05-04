package woowacourse.movie.ui.movielist.contract

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListItem

interface MovieListContract {
    interface Presenter {
        fun loadMovieListItems()
    }

    interface View {
        fun setMoveListItems(items: List<MovieListItem>)

        fun startBookingActivity(movie: Movie)
    }
}
