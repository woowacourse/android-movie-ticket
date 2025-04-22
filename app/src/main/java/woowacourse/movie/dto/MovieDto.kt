package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration

@Parcelize
data class MovieDto(
    val title: String,
    val drawable: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val runningTime: Duration,
) : Parcelable {
    fun toMovie(): Movie {
        return Movie(
            title = title,
            posterUrl = drawable,
            startDateTime = startDateTime,
            endDateTime = endDateTime,
            runningTime = runningTime,
        )
    }

    companion object {
        fun fromMovie(movie: Movie): MovieDto {
            return MovieDto(
                title = movie.title,
                drawable = movie.posterUrl,
                startDateTime = movie.startDateTime,
                endDateTime = movie.endDateTime,
                runningTime = movie.runningTime,
            )
        }
    }
}
