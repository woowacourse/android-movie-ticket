package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.MovieListData
import woowacourse.movie.domain.MovieListData.Ads
import woowacourse.movie.domain.MovieListData.Movie
import woowacourse.movie.domain.rules.PriceRule
import java.time.LocalDateTime
import java.util.UUID
import kotlin.time.Duration

sealed class MovieListDataDto(
    val id: UUID = UUID.randomUUID(),
) {
    @Parcelize
    data class MovieDto(
        val title: String,
        val drawable: String,
        val startDateTime: LocalDateTime,
        val endDateTime: LocalDateTime,
        val runningTime: Duration,
        val priceRuleDto: List<PriceRuleDto>,
    ) : Parcelable, MovieListDataDto() {
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
                    priceRuleDto = PriceRule.entries.map { PriceRuleDto.fromPriceRule(it) },
                )
            }
        }
    }

    @Parcelize
    data class AdsDto(
        val uri: String,
    ) : Parcelable, MovieListDataDto()

    companion object {
        fun fromDomain(data: List<MovieListData>): List<MovieListDataDto> =
            data.map {
                when (it) {
                    is Movie -> MovieDto.fromMovie(it)
                    is Ads -> AdsDto(it.uri)
                }
            }
    }
}
