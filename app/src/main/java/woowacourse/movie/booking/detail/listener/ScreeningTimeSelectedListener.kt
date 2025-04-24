package woowacourse.movie.booking.detail.listener

import android.view.View
import android.widget.AdapterView
import java.time.LocalTime

class ScreeningTimeSelectedListener(
    private val onTimeSelected: (LocalTime) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selectedTime = parent?.getItemAtPosition(position) as? LocalTime ?: return
        onTimeSelected(selectedTime)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
