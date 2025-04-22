package woowacourse.movie.view.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.TicketUiModel

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(title, startDate, endDate, runningTime, R.drawable.harry_potter_poster)
}

fun MovieUiModel.toDomain(): Movie {
    return Movie(title, startDate, endDate, runningTime)
}

fun Ticket.toUiModel(): TicketUiModel {
    return TicketUiModel(movie.toUiModel(), showtime, count)
}

fun TicketUiModel.toDomain(): Ticket {
    return Ticket(movie.toDomain(), showtime, count)
}
