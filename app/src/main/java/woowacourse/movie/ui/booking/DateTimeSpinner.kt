package woowacourse.movie.ui.booking

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeSpinner(
    context: Context,
    attrs: AttributeSet,
) : ConstraintLayout(context, attrs) {
    private var layoutInflater: LayoutInflater? = null
    private val dateSpinner: Spinner by lazy { findViewById(R.id.movieDateSpinner) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.movieTimeSpinner) }
    private val dateAdapter: SpinnerAdapter<LocalDate> by lazy {
        SpinnerAdapter(context, R.layout.screening_date_time_item, R.id.textSpinnerDateTime)
    }
    private val timeAdapter: SpinnerAdapter<LocalTime> by lazy {
        SpinnerAdapter(context, R.layout.screening_date_time_item, R.id.textSpinnerDateTime)
    }

    private val selectedDateItem: LocalDate? get() = dateAdapter.getItem(dateSpinner.selectedItemPosition)
    private val selectedTimeItem: LocalTime? get() = timeAdapter.getItem(timeSpinner.selectedItemPosition)
    val selectedDateTime: LocalDateTime get() = LocalDateTime.of(selectedDateItem, selectedTimeItem)

    init {
        initView()
        initAdapters()
    }

    private fun initView() {
        layoutInflater ?: run {
            layoutInflater = LayoutInflater.from(context)
            layoutInflater?.inflate(R.layout.date_time_spinner, this, true)
        }
    }

    private fun initAdapters() {
        dateSpinner.adapter = dateAdapter
        timeSpinner.adapter = timeAdapter
    }

    fun initDateItems(dates: List<LocalDate>) {
        dateAdapter.initItems(dates)
    }

    fun initTimeItems(times: List<LocalTime>) {
        timeAdapter.initItems(times)
    }

    fun initDateSelectedListener(getTimes: (LocalDate) -> List<LocalTime>) {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val times = getTimes(dateAdapter.getItem(position) ?: return)
                initTimeItems(times)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }
}
