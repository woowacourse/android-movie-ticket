package woowacourse.movie.extension

import android.content.Intent
import woowacourse.movie.movieReservation.MovieReservationActivity
import woowacourse.movie.movieTicket.MovieTicketActivity
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.uimodel.MovieTicketUi

fun Intent.getSerializableScheduleOrNull(): MovieModelUi.MovieScheduleUi? {
    return getSerializableExtra(MovieReservationActivity.KEY_MOVIE_SCHEDULE) as? MovieModelUi.MovieScheduleUi
}

fun Intent.getSerializableTicketOrNull(): MovieTicketUi? {
    return getSerializableExtra(MovieTicketActivity.KEY_MOVIE_TICKET) as? MovieTicketUi
}
