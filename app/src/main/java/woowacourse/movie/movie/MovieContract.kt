package woowacourse.movie.movie

import android.content.Intent
import woowacourse.movie.model.Movie

interface MovieContract {
    interface View {
        fun showMovies(movies: List<Movie>)

        fun showToast(message: String)

        fun startBookingActivity(movie: Movie)
    }

    interface Presenter {
        fun initializeData(intent: Intent)

        fun onReserveClicked(movie: Movie)
    }
}