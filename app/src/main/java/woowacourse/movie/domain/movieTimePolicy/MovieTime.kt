package woowacourse.movie.domain.movieTimePolicy

import java.time.LocalDate
import java.time.LocalTime

class MovieTime(private val movieTimePolicies: List<MovieTimePolicy>) {
    fun determine(date: LocalDate): List<LocalTime> {
        for (item in movieTimePolicies) {
            val movieTime = item.generateTime(date)
            if (movieTime.isNotEmpty()) return movieTime
        }
        throw IllegalStateException(ERROR_MOVIE_TIME)
    }
    companion object {
        private const val ERROR_MOVIE_TIME = "영화 시간 정책이 잘못 설정 되었습니다."
    }
}
