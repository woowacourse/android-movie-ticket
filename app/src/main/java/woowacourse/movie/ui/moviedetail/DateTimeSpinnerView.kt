package woowacourse.movie.ui.moviedetail

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.domain.TimesGenerator
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.MovieListModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeSpinnerView(
    private val dateSpinner: Spinner,
    private val timeSpinner: Spinner,
) {
    private val times = mutableListOf<LocalTime>()
    private lateinit var timeSpinnerAdapter: ArrayAdapter<LocalTime>

    val dateSelectedPosition: Int
        get() = dateSpinner.selectedItemPosition

    val timeSelectedPosition: Int
        get() = timeSpinner.selectedItemPosition

    val selectedItem: LocalDateTime
        get() = LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate,
            timeSpinner.selectedItem as LocalTime
        )

    fun setItemPosition(datePosition: Int, timePosition: Int) {
        dateSpinner.setSelection(datePosition)
        timeSpinner.setSelection(timePosition)
    }

    fun setDateSpinner(movie: MovieListModel.MovieModel) {
        val dateSpinnerAdapter = ArrayAdapter(
            dateSpinner.context,
            android.R.layout.simple_spinner_item,
            movie.toDomain().getDatesBetweenTwoDates()
        )
        dateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dateSpinner.adapter = dateSpinnerAdapter
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                timeSpinner.setSelection(0)
                times.clear()
                times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
                timeSpinnerAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    fun setTimeSpinner() {
        times.addAll(TimesGenerator.getTimesByDate(dateSpinner.selectedItem as LocalDate))
        timeSpinnerAdapter = ArrayAdapter(
            timeSpinner.context,
            android.R.layout.simple_spinner_item,
            times
        )
        timeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeSpinnerAdapter
    }
}
