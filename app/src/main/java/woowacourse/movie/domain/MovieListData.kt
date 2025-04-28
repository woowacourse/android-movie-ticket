package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration

sealed class MovieListData {
    data class Ads(
        val uri: String,
    ) : MovieListData()

    data class Movie(
        val title: String,
        val posterUrl: String,
        val startDateTime: LocalDateTime,
        val endDateTime: LocalDateTime,
        val runningTime: Duration,
    ) : MovieListData() {
        fun betweenDates(targetDate: LocalDate = LocalDate.now()): List<LocalDate> =
            buildList {
                val startDate = startDateTime.toLocalDate()
                val endDate = endDateTime.toLocalDate()

                var standardDate = targetDate
                if (standardDate.isBefore(startDate)) standardDate = startDate
                if (standardDate.isAfter(endDate)) throw IllegalStateException("이미 상영이 종료된 영화입니다.")
                while (!standardDate.isAfter(endDate)) {
                    add(standardDate)
                    standardDate = standardDate.plusDays(1)
                }
            }
    }

    companion object {
        private val isAds = { position: Int -> (position + 1) % 4 == 0 }

        fun flatten(
            movies: List<Movie>,
            ads: List<Ads>,
        ): List<MovieListData> =
            buildList {
                var currentAdIndex = 0
                movies.forEachIndexed { index, movie ->
                    if (isAds(index)) {
                        add(ads[currentAdIndex % ads.size])
                        currentAdIndex++
                    } else {
                        add(movie)
                    }
                }
            }
    }
}
