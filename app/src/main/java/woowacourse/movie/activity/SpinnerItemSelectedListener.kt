package woowacourse.movie.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import domain.movie.ScreeningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SpinnerItemSelectedListener(
    private val screeningTimePosition: Int,
    private val onScreeningDateSelected: (date: ScreeningDate?, defaultPosition: Int) -> Unit
) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val screeningDate = ScreeningDate(
            LocalDate.parse(
                (parent as Spinner).selectedItem.toString(),
                DateTimeFormatter.ISO_DATE
            )
        )

        onScreeningDateSelected(
            screeningDate,
            screeningTimePosition
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onScreeningDateSelected(null, 0)
    }
}
