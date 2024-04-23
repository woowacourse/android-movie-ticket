package woowacourse.movie.presentation.dto

import woowacourse.movie.presentation.utils.dateToString
import java.time.LocalDate

data class MovieTicketUiModel(
    val movieTitle: String,
    val screeningDate: String,
    val reservationCount: Int,
    val totalPrice: Int,
) {
    companion object {
        fun fromMovieTicket(
            movieTitle: String,
            screeningDate: LocalDate,
            reservationCount: Int,
            totalPrice: Int,
        ): MovieTicketUiModel {
            return MovieTicketUiModel(
                movieTitle = movieTitle,
                screeningDate = screeningDate.dateToString(),
                reservationCount = reservationCount,
                totalPrice = totalPrice,
            )
        }
    }
}
