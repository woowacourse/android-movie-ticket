package woowacourse.movie.mapper

import android.content.res.Resources
import woowacourse.movie.R
import woowacourse.movie.model.BookingResult
import woowacourse.movie.ui.BookingResultUiModel
import woowacourse.movie.util.Formatter.moneyFormat
import woowacourse.movie.util.Formatter.simpleDateFormat
import woowacourse.movie.util.Formatter.simpleTimeFormat

fun BookingResult.toUiModel(resources: Resources): BookingResultUiModel {
    val selectedDateText = simpleDateFormat(selectedDate)
    val selectedTimeText = simpleTimeFormat(selectedTime)
    val bookingAmount = moneyFormat(calculateAmount())

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
