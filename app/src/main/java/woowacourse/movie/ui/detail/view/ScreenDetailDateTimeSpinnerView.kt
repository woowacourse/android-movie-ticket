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
import woowacourse.movie.domain.model.ScreenTimePolicy
import woowacourse.movie.ui.detail.ScreenDetailContract
import java.time.LocalDate
import java.time.LocalTime

class ScreenDetailDateTimeSpinnerView(context: Context, attrs: AttributeSet? = null) : DateTimeSpinnerView,
    ConstraintLayout(context, attrs) {
    private lateinit var dateAdapter: ArrayAdapter<LocalDate>
    lateinit var timeAdapter: ArrayAdapter<LocalTime>

    private val dateSpinner: Spinner by lazy { findViewById(R.id.spn_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spn_time) }

    init {
        inflate(context, R.layout.holder_spinner_date_time, this)
    }

    override fun show(
        dateRange: DateRange,
        screenTimePolicy: ScreenTimePolicy,
        selectDateListener: SelectDateListener,
        selectTimeListener: SelectTimeListener,
    ) {
        initDateAdapter(dateRange)
        initTimeAdapter(dateRange.start, screenTimePolicy)

        initDateSpinnerSelection(screenTimePolicy, selectDateListener)
        initTimeSpinnerSelection(selectTimeListener)
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

    private fun initTimeAdapter(
        date: LocalDate,
        screenTimePolicy: ScreenTimePolicy,
    ) {
        timeAdapter =
            ArrayAdapter(context, android.R.layout.simple_spinner_item, screenTimePolicy.screeningTimes(date).toList())
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter
    }

    private fun initDateSpinnerSelection(
        screenTimePolicy: ScreenTimePolicy,
        selectDateListener: SelectDateListener,
    ) {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val date = dateAdapter.getItem(position)
                    date?.let {
                        timeAdapter.clear()
                        timeAdapter.addAll(screenTimePolicy.screeningTimes(date).toList())
                    }
                    selectDateListener.selectDate(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
                }
            }
    }

    private fun initDateSpinnerSelection(
        screenTimePolicy: ScreenTimePolicy,
        presenter: ScreenDetailContract.Presenter,
    ) {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val date = dateAdapter.getItem(position)
                    date?.let {
                        timeAdapter.clear()
                        timeAdapter.addAll(screenTimePolicy.screeningTimes(date).toList())
                    }
                    presenter.saveDatePosition(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
                }
            }
    }

    private fun initTimeSpinnerSelection(selectTimeListener: SelectTimeListener) {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectTimeListener.selectTime(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
                }
            }
    }

    private fun initTimeSpinnerSelection(presenter: ScreenDetailContract.Presenter) {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.saveTimePosition(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.e("ScreenDetailDateTimeSpinnerView", "Nothing Selected")
                }
            }
    }
}
