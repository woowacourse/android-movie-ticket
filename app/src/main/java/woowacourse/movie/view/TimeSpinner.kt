package woowacourse.movie.view

import android.os.Bundle
import android.widget.Spinner
import domain.movieTimePolicy.MovieTime
import domain.movieTimePolicy.WeekdayMovieTime
import domain.movieTimePolicy.WeekendMovieTime
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
