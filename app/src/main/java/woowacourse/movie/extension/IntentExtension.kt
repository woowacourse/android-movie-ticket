package woowacourse.movie.extension

import android.content.Intent
import woowacourse.movie.movieReservation.MovieReservationActivity.Companion.KEY_MOVIE_SCHEDULE
import woowacourse.movie.movieSeat.MovieSeatActivity.Companion.KEY_MOVIE_DETAIL
import woowacourse.movie.movieTicket.MovieTicketActivity.Companion.KEY_MOVIE_TICKET
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.uimodel.MovieTicketUi

fun Intent.getSerializableScheduleOrNull(): MovieModelUi.MovieScheduleUi? {
    return getSerializableExtra(KEY_MOVIE_SCHEDULE) as? MovieModelUi.MovieScheduleUi
}

fun Intent.getSerializableMovieDetailOrNull(): MovieDetailUi? {
    return getSerializableExtra(KEY_MOVIE_DETAIL) as? MovieDetailUi
}

fun Intent.getSerializableTicketOrNull(): MovieTicketUi? {
    return getSerializableExtra(KEY_MOVIE_TICKET) as? MovieTicketUi
}
