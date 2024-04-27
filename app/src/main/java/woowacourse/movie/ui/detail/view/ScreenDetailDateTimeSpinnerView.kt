package woowacourse.movie.ui.detail.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.domain.model.DateRange
import java.lang.IllegalStateException
import java.time.DayOfWeek
import java.time.LocalDate

class ScreenDetailDateTimeSpinnerView(context: Context, attrs: AttributeSet? = null) : DateTimeSpinnerView,
    ConstraintLayout(context, attrs) {

    private lateinit var dateAdapter: ArrayAdapter<LocalDate>
    private lateinit var timeAdapter: ArrayAdapter<CharSequence>

    private val dateSpinner: Spinner by lazy { findViewById(R.id.spn_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spn_time) }

    init {
        inflate(context, R.layout.holder_spinner_date_time, this)
    }

    override fun show(dateRange: DateRange) {
        initDateAdapter(dateRange)
        initTimeAdapter(dateRange.start)

        initDateSpinnerSelection()
        initTimeSpinnerSelection()
    }


    override fun restoreDatePosition(position: Int) {
        dateSpinner.setSelection(position)
    }

    override fun restoreTimePosition(position: Int) {
        timeSpinner.setSelection(position)
    }

    override fun selectedDatePosition(): Int {
        return dateSpinner.selectedItemPosition
    }

    override fun selectedTimePosition(): Int {
        return timeSpinner.selectedItemPosition
    }

    private fun initDateAdapter(dateRange: DateRange) {
        dateAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, dateRange.allDates())
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateAdapter
    }

    private fun initTimeAdapter(date: LocalDate) {
        timeAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, decideTime(isWeekDay(date)).toList())
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter
    }

    private fun initDateSpinnerSelection() {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val date = dateAdapter.getItem(position)
                date?.let {
                    timeAdapter.clear()
                    timeAdapter.addAll(decideTime(isWeekDay(date)))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
            }
        }
    }

    private fun initTimeSpinnerSelection() {
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // TODO: 로직 구현해야 함
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
            }
        }
    }


    private fun decideTime(isWeek: Boolean): List<String> = if (isWeek) {
        weekDayTimes
    } else {
        weekEndTimes
    }

    private fun isWeekDay(date: LocalDate?): Boolean = date?.let {
        it.dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY
    } ?: throw IllegalStateException("Spinner's item is null!!")

    private val weekDayTimes = listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
    private val weekEndTimes = listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
}



