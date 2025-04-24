package woowacourse.movie.mapper

import android.content.res.Resources
import woowacourse.movie.R
import woowacourse.movie.model.BookingResult
import woowacourse.movie.booking.detail.BookingResultUiModel
import woowacourse.movie.util.Formatter.formatDateDotSeparated
import woowacourse.movie.util.Formatter.formatMoney
import woowacourse.movie.util.Formatter.formatTimeWithMidnight24

fun BookingResult.toUiModel(resources: Resources): BookingResultUiModel {
    val selectedDateText = formatDateDotSeparated(selectedDate)
    val selectedTimeText = formatTimeWithMidnight24(selectedTime)
    val bookingAmount = formatMoney(calculateAmount())

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
