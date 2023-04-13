package woowacourse.movie.view

import android.content.Context
import android.os.Bundle
import woowacourse.movie.domain.movieTimePolicy.MovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekdayMovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekendMovieTime
import java.time.LocalDate

class TimeSpinner(val spinner: SaveStateSpinner) {
    fun make(context: Context, savedInstanceState: Bundle?, date: LocalDate) {
        val times = MovieTime(
            listOf(WeekdayMovieTime, WeekendMovieTime)
        ).determine(date).map { LocalFormattedTime(it) }
        spinner.initSpinner(context, times)
        spinner.load(savedInstanceState)
    }

    fun save(savedInstanceState: Bundle) {
        spinner.save(savedInstanceState)
    }
}
