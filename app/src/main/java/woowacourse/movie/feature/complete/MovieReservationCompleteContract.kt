package woowacourse.movie.feature.complete

import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener
import java.time.LocalDateTime

interface MovieReservationCompleteContract {
    interface View : ErrorListener {
        fun setUpReservationCompleteView(
            movie: Movie,
            ticket: Ticket,
        )
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(
            movieId: Long,
            reservationTime: LocalDateTime,
            reservationCountValue: Int,
        )
    }
}
