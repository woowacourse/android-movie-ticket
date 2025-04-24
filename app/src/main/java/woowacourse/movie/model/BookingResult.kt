package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class BookingResult(
    val title: String,
    val headCount: Int,
    val selectedDate: LocalDate,
    val selectedTime: LocalTime,
) : Parcelable {
    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLANK_MESSAGE }
    }

    fun calculateAmount(): Int {
        return TICKET_PRICE * headCount
    }

    fun plusHeadCount(): BookingResult = copy(headCount = headCount + 1)

    fun minusHeadCount(): BookingResult = copy(headCount = headCount - 1)

    fun updateDate(date: LocalDate): BookingResult = copy(selectedDate = date)

    fun updateTime(time: LocalTime): BookingResult = copy(selectedTime = time)

    fun isHeadCountValid(): Boolean = headCount > 0

    companion object {
        private const val TICKET_PRICE = 13_000
        private const val ERROR_TITLE_BLANK_MESSAGE = "예매한 영화 제목은 비어 있을 수 없다"
    }
}