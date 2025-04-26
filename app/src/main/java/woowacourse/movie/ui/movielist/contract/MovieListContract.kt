package woowacourse.movie.ui.movielist.contract

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListItem

interface MovieListContract {
    interface Presenter {
        fun updateMovieList()

        fun getMovieList(): List<MovieListItem.MovieItem>

        fun getAdvertisementList(): List<MovieListItem.AdItem>
    }

    interface View {
        fun setMoveListItems(items: List<MovieListItem>)

        fun moveToBookingActivity(movie: Movie)
    }
}
