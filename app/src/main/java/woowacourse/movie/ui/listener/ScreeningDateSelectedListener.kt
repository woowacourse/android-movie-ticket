package woowacourse.movie.ui.listener

import android.view.View
import android.widget.AdapterView
import woowacourse.movie.model.BookingResult
import java.time.LocalDate

class ScreeningDateSelectedListener(
    private var bookingResult: BookingResult,
    private val onBookingResultChanged: (BookingResult) -> Unit,
    private val refreshTimeSpinner: () -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selectedDate = parent?.getItemAtPosition(position) as LocalDate
        bookingResult = bookingResult.updateDate(selectedDate)
        onBookingResultChanged(bookingResult)
        refreshTimeSpinner()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
