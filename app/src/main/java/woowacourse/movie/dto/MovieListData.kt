package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.PriceRule
import java.time.LocalDateTime
import java.util.UUID
import kotlin.time.Duration

sealed class MovieListData(
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
    ) : Parcelable, MovieListData() {
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
    ) : Parcelable, MovieListData()

    companion object {
        private val isAds = { position: Int -> (position + 1) % 4 == 0 }

        fun flatten(
            movieDtos: List<MovieDto>,
            adsDto: List<AdsDto>,
        ): List<MovieListData> =
            buildList {
                var currentAdIndex = 0
                movieDtos.forEachIndexed { index, movieDto ->
                    if (isAds(index)) {
                        add(adsDto[currentAdIndex % adsDto.size])
                        currentAdIndex++
                    } else {
                        add(movieDto)
                    }
                }
            }
    }
}
