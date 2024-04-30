package woowacourse.movie.seatselection.presenter.contract

import woowacourse.movie.model.MovieSeat
import woowacourse.movie.model.MovieSelectedSeats

interface MovieSeatSelectionContract {
    interface View {
        fun displayMovieTitle(movieTitle: String)

        fun setUpTableSeats(baseSeats: List<MovieSeat>)

        fun updateSeatBackgroundColor(
            index: Int,
            isSelected: Boolean,
        )

        fun displayDialog()

        fun updateSelectResult(movieSelectedSeats: MovieSelectedSeats)

        fun navigateToResultView(movieSelectedSeats: MovieSelectedSeats)
    }

    interface Presenter {
        fun loadMovieTitle(id: Long)

        fun loadTableSeats(count: Int)

        fun clickTableSeat(index: Int)

        fun clickPositiveButton()

        fun updateSelectedSeats(count: Int)
    }
}
