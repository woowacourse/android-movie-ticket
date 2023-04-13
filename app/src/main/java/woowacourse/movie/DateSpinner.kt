package woowacourse.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import woowacourse.movie.domain.Movie

class DateSpinner(val spinner: SaveStateSpinner) {
    fun make(
        context: Context,
        savedInstanceState: Bundle?,
        movie: Movie,
        timeSpinner: TimeSpinner
    ) {
        val dates = movie.date.toList().map { LocalFormattedDate(it) }

        spinner.initSpinner(context, dates)
        spinner.load(savedInstanceState)

        spinner.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                timeSpinner.make(context, savedInstanceState, dates[p2].date)
            }
        }
    }

    fun save(savedInstanceState: Bundle) {
        spinner.save(savedInstanceState)
    }
}
