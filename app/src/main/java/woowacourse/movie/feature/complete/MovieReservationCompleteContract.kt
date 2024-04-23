package woowacourse.movie.feature.complete

import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface MovieReservationCompleteContract {
    interface View : ErrorListener {
        fun setUpReservationCompleteView(
            movieContent: MovieContent,
            ticket: Ticket,
        )
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(
            movieContentId: Long,
            reservationCountValue: Int,
        )
    }
}
