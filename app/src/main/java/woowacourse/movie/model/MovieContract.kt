package woowacourse.movie.model

import android.content.Intent

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
