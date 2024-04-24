package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Grade
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat

interface SeatSelectionContract {
    interface Presenter {
        fun loadSeatNumber()

        fun loadMovie(movieId: Int)
    }

    interface View {
        fun showSeatNumber(
            index: Int,
            seat: Seat,
        )

        fun setUpSeatColorByGrade(grade: Grade): Int

        fun updateSeatSelectedState(
            index: Int,
            isSelected: Boolean,
        )

        fun setConfirmButtonEnabled(count: Int)

        fun showMovieTitle(movie: Movie)
    }
}
