package woowacourse.movie

import android.content.Context
import android.os.Bundle
import woowacourse.movie.domain.movieTimePolicy.MovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekDayMovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekendMovieTime
import java.time.LocalDate

class TimeSpinner(val spinner: SaveStateSpinner) {
    fun make(context: Context, savedInstanceState: Bundle?, date: LocalDate) {
        val times = MovieTime(
            listOf(WeekDayMovieTime, WeekendMovieTime)
        ).determine(date).map { LocalFormattedTime(it) }
        spinner.initSpinner(context, times)
        spinner.load(savedInstanceState)
    }

    fun save(savedInstanceState: Bundle) {
        spinner.save(savedInstanceState)
    }
}
