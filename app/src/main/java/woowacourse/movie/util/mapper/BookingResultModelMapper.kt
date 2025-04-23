package woowacourse.movie.util.mapper

import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER

object BookingResultModelMapper {
    fun toDomain(bookingResultUiModel: BookingResultUiModel): BookingResult {
        return BookingResult(
            title = bookingResultUiModel.title,
            headCount = bookingResultUiModel.headCount.toInt(),
            selectedDate = DateTimeUtil.toLocalDate(bookingResultUiModel.selectedDate, MOVIE_DATE_DELIMITER),
            selectedTime = DateTimeUtil.toLocalTime(bookingResultUiModel.selectedTime, MOVIE_TIME_DELIMITER),
        )
    }

    fun toUi(bookingResult: BookingResult): BookingResultUiModel {
        return BookingResultUiModel(
            title = bookingResult.title,
            headCount = bookingResult.headCount.toString(),
            selectedDate =
                DateTimeUtil.toFormattedString(
                    bookingResult.selectedDate,
                    DateTimeUtil.MOVIE_DATE_FORMAT,
                ),
            selectedTime =
                DateTimeUtil.toFormattedString(
                    bookingResult.selectedTime,
                    DateTimeUtil.MOVIE_TIME_FORMAT,
                ),
        )
    }
}
