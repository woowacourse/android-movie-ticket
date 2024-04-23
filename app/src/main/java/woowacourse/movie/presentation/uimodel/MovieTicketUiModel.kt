package woowacourse.movie.presentation.dto

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presentation.utils.dateToString

data class MovieTicketUiModel(
    val movieTitle: String,
    val screeningDate: String,
    val reservationCount: Int,
    val totalPrice: Int,
) {
    companion object {
        fun fromMovieTicket(movieTicket: MovieTicket): MovieTicketUiModel {
            return MovieTicketUiModel(
                movieTitle = movieTicket.movieTitle,
                screeningDate = movieTicket.screeningDate.dateToString(),
                reservationCount = movieTicket.reservationCount,
                totalPrice = movieTicket.totalPrice(),
            )
        }
    }
}
