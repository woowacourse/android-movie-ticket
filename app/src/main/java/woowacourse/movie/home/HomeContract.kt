package woowacourse.movie.home

import woowacourse.movie.model.Movie

interface HomeContract {
    interface View {
        fun moveToReservationDetail(movieId: Int)
    }

    interface Presenter {
        fun obtainMovies(): List<Movie>

        fun deliverMovie(movieId: Int)
    }
}
