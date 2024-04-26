package woowacourse.movie.feature.complete

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationCompleteContract {
    interface View : ErrorListener {
        fun setUpReservationCompleteView(movie: Movie)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)
    }
}
