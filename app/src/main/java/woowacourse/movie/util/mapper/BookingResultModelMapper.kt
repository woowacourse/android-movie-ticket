package woowacourse.movie.util.mapper

import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.DateTimeUtil.toFormattedString
import woowacourse.movie.util.DateTimeUtil.toLocalDate
import woowacourse.movie.util.DateTimeUtil.toLocalTime

object BookingResultModelMapper {
    fun toDomain(bookingResultUiModel: BookingResultUiModel): BookingResult {
        return BookingResult(
            title = bookingResultUiModel.title,
            headCount = bookingResultUiModel.headCount.toInt(),
            selectedDate = bookingResultUiModel.selectedDate.toLocalDate(MOVIE_DATE_DELIMITER),
            selectedTime = bookingResultUiModel.selectedTime.toLocalTime(MOVIE_TIME_DELIMITER),
        )
    }

    fun toUi(bookingResult: BookingResult): BookingResultUiModel {
        return BookingResultUiModel(
            title = bookingResult.title,
            headCount = bookingResult.headCount.toString(),
            selectedDate =
                bookingResult.selectedDate.toFormattedString(DateTimeUtil.MOVIE_DATE_FORMAT),
            selectedTime =
                bookingResult.selectedTime.toFormattedString(DateTimeUtil.MOVIE_TIME_FORMAT),
        )
    }
}
