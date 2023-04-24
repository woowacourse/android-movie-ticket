package woowacourse.movie.view.widget

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import woowacourse.movie.view.data.LocalFormattedDate
import woowacourse.movie.view.data.MovieViewData

class DateSpinner(val spinner: SaveStateSpinner) {
    fun make(
        savedInstanceState: Bundle?,
        movie: MovieViewData,
        timeSpinner: TimeSpinner
    ) {
        val dates = movie.date.toList().map { LocalFormattedDate(it) }

        spinner.initSpinner(dates)
        spinner.load(savedInstanceState)

        spinner.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                timeSpinner.make(savedInstanceState, dates[p2].date)
            }
        }
    }

    fun save(savedInstanceState: Bundle) {
        spinner.save(savedInstanceState)
    }
}
