package woowacourse.movie.ui.listener

import android.view.View
import android.widget.AdapterView
import woowacourse.movie.model.BookingResult
import java.time.LocalTime

class ScreeningTimeSelectedListener(
    private var bookingResult: BookingResult,
    private val onBookingResultChanged: (BookingResult) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selectedTime = parent?.getItemAtPosition(position) as LocalTime
        bookingResult = bookingResult.updateTime(selectedTime)
        onBookingResultChanged(bookingResult)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
