package woowacourse.movie.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import domain.Movie
import java.time.LocalDate

class DateSpinner(spinner: Spinner, savedStateKey: String) :
    SaveStateSpinner(savedStateKey, spinner) {
    fun make(
        savedInstanceState: Bundle?,
        movie: Movie,
        timeSpinner: TimeSpinner
    ) {
        val dates = movie.date.toList().map { LocalFormattedDate(it) }
        setArrayAdapter(dates)
        load(savedInstanceState)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                timeSpinner.make(savedInstanceState, dates[p2].date)
            }
        }
    }

    fun getSelectedDate(): LocalDate {
        return (spinner.selectedItem as LocalFormattedDate).date
    }
}
