package woowacourse.movie.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import domain.movie.ScreeningDate
import woowacourse.movie.activity.ReservationActivity.Companion.SCREENING_TIME_POINT_KEY
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScreeningSpinnerOption(
    private val savedInstanceState: Bundle?,
    private val screeningDateSpinner: Spinner,
    private val initTimeSpinner: (date: ScreeningDate?, defaultPoint: Int) -> Unit
) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val screeningDate = ScreeningDate(
            LocalDate.parse(
                screeningDateSpinner.selectedItem.toString(),
                DateTimeFormatter.ISO_DATE
            )
        )
        val selectedScreeningTimePosition =
            savedInstanceState?.getInt(SCREENING_TIME_POINT_KEY) ?: 0

        initTimeSpinner(
            screeningDate,
            selectedScreeningTimePosition
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        initTimeSpinner(null, 0)
    }
}
