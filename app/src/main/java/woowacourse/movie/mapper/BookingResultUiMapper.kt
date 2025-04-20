package woowacourse.movie.mapper

import android.content.res.Resources
import android.icu.text.DecimalFormat
import woowacourse.movie.R
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.BookingResultUiModel
import java.time.format.DateTimeFormatter

fun BookingResult.toUiModel(resources: Resources): BookingResultUiModel {
    val selectedDateText = selectedDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    val selectedTimeText = selectedTime.format(DateTimeFormatter.ofPattern("KK:mm"))
    val bookingAmount = DecimalFormat("#,###").format(calculateAmount())

    return BookingResultUiModel(
        title = title,
        headCount = resources.getString(R.string.screening_complete_headCount, headCount),
        selectedDateText = selectedDateText,
        selectedTimeText = selectedTimeText,
        bookingAmountText =
            resources.getString(
                R.string.screening_complete_booking_amount,
                bookingAmount,
            ),
    )
}
