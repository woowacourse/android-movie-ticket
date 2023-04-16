package woowacourse.movie.view

import android.os.Bundle
import android.widget.Spinner
import woowacourse.movie.domain.movieTimePolicy.MovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekdayMovieTime
import woowacourse.movie.domain.movieTimePolicy.WeekendMovieTime
import java.time.LocalDate
import java.time.LocalTime

class TimeSpinner(spinner: Spinner, savedStateKey: String) :
    SaveStateSpinner(savedStateKey, spinner) {
    fun make(savedInstanceState: Bundle?, date: LocalDate) {
        val times = MovieTime(
            listOf(WeekdayMovieTime, WeekendMovieTime)
        ).determine(date).map { LocalFormattedTime(it) }
        setArrayAdapter(times)
        load(savedInstanceState)
    }

    fun getSelectedTime(): LocalTime {
        return (spinner.selectedItem as LocalFormattedTime).time
    }
}
