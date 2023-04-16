package woowacourse.movie.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import domain.movie.ScreeningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SpinnerItemSelectedListener(
    private val screeningDateSpinner: Spinner,
    private val screeningTimePosition: Int,
    private val initTimeSpinner: (date: ScreeningDate?, defaultPosition: Int) -> Unit
) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val screeningDate = ScreeningDate(
            LocalDate.parse(
                screeningDateSpinner.selectedItem.toString(),
                DateTimeFormatter.ISO_DATE
            )
        )

        initTimeSpinner(
            screeningDate,
            screeningTimePosition
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        initTimeSpinner(null, 0)
    }
}
