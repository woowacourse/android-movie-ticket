package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface SeatSelectContract {
    interface View : ErrorListener {
        fun initializeMovie(movie: SeatSelectMovieUiModel)

        fun initializeSeatTable(seats: List<List<SeatSelectTableUiModel>>)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun initializeSeatTable(
            row: Int,
            col: Int,
        )
    }
}
