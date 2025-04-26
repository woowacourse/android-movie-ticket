package woowacourse.movie.movie

import android.content.Intent

interface MovieContract {
    interface View {
        fun showMovies(movies: List<MovieUiModel>)

        fun showToast(message: String)

        fun startBookingActivity(movie: MovieUiModel)
    }

    interface Presenter {
        fun initializeData(intent: Intent)

        fun onReserveClicked(movie: MovieUiModel)
    }
}
