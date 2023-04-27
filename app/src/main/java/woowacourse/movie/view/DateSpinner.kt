package woowacourse.movie.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import woowacourse.movie.view.model.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime

class DateSpinner(spinner: Spinner, savedStateKey: String) :
    SaveStateSpinner(savedStateKey, spinner) {
    fun make(
        savedInstanceState: Bundle?,
        movieUiModel: MovieUiModel,
        timeSpinner: TimeSpinner
    ) {
        val dates = movieUiModel.getDateList().map { LocalFormattedDate(it) }
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
