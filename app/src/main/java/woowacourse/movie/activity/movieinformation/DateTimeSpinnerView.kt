package woowacourse.movie.activity.movieinformation

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.ReservationModel
import java.time.LocalDate
import java.time.LocalTime

class DateTimeSpinnerView(
    private val binding: ActivityReservationBinding
) {

    fun getSelectedDate() = binding.screeningDateSpinner.selectedItem as LocalDate

    fun getSelectedTime() = binding.screeningTimeSpinner.selectedItem as LocalTime

    fun set(movieModel: MovieModel, savedInstanceState: Bundle?) {
        initSpinner(movieModel, savedInstanceState)
    }

    fun loadSpinner(movieModel: MovieModel, savedInstanceState: Bundle) {
        val screeningDate: LocalDate =
            LocalDate.ofEpochDay(savedInstanceState.getLong(ReservationModel.SCREENING_DATE_INSTANCE_KEY))
        val screeningTime: LocalTime =
            LocalTime.parse(savedInstanceState.getString(ReservationModel.SCREENING_TIME_INSTANCE_KEY))
        val selectedDatePosition: Int =
            movieModel.screeningPeriod.getScreeningDates().indexOf(screeningDate)
        val selectedTimePosition: Int =
            movieModel.screeningPeriod.getScreeningTimes(screeningDate).indexOf(screeningTime)

        binding.screeningDateSpinner.setSelection(selectedDatePosition)
        initTimeSpinner(movieModel, screeningDate, selectedTimePosition)
    }

    private fun initSpinner(movieModel: MovieModel, savedInstanceState: Bundle?) {
        val dates = movieModel.screeningPeriod.getScreeningDates()

        binding.screeningDateSpinner.adapter = ArrayAdapter(
            binding.root.context, R.layout.simple_spinner_item, dates
        )

        binding.screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    initTimeSpinner(movieModel, dates[position])
                    if (savedInstanceState != null) {
                        loadSpinner(movieModel, savedInstanceState)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    initTimeSpinner(movieModel, null)
                }
            }
    }

    private fun initTimeSpinner(movieModel: MovieModel, date: LocalDate?, position: Int = 0) {
        val times = movieModel.screeningPeriod.getScreeningTimes(date)

        binding.screeningTimeSpinner.adapter = ArrayAdapter(
            binding.root.context, R.layout.simple_spinner_item, times
        )
        binding.screeningTimeSpinner.setSelection(position)
    }
}
