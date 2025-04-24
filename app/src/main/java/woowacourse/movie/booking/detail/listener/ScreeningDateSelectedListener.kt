package woowacourse.movie.booking.detail.listener

import android.view.View
import android.widget.AdapterView
import java.time.LocalDate

class ScreeningDateSelectedListener(
    private val onDateSelected: (LocalDate) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selectedDate = parent?.getItemAtPosition(position) as LocalDate
        onDateSelected(selectedDate)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
