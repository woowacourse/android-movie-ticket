package woowacourse.movie.view

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

fun Movie.toParcelable(): ParcelableMovie {
    return ParcelableMovie(title, startDate, endDate, runningTime, R.drawable.harry_potter_poster)
}

fun ParcelableMovie.toModel(): Movie {
    return Movie(title, startDate, endDate, runningTime)
}

fun Ticket.toParcelable(): ParcelableTicket {
    return ParcelableTicket(movie.toParcelable(), showtime, count)
}

fun ParcelableTicket.toModel(): Ticket {
    return Ticket(movie.toModel(), showtime, count)
}
