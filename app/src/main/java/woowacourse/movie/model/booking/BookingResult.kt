package woowacourse.movie.model.booking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingResult(
    val title: String,
    val headCount: Int,
    val selectedDate: String,
    val selectedTime: String,
) : Parcelable {
    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLANK_MESSAGE }
    }

    fun calculateAmount(): Int {
        return TICKET_PRICE * headCount
    }

    fun plusHeadCount(): BookingResult {
        return this.copy(headCount = headCount + 1)
    }

    fun minusHeadCount(): BookingResult {
        return this.copy(headCount = headCount - 1)
    }

    fun updateDate(date: String): BookingResult {
        return this.copy(selectedDate = date)
    }

    fun updateTime(time: String): BookingResult {
        return this.copy(selectedTime = time)
    }

    fun isHeadCountValid() = headCount > 0

    companion object {
        private const val TICKET_PRICE = 13_000
        private const val ERROR_TITLE_BLANK_MESSAGE = "예매한 영화 제목은 비어 있을 수 없다"
    }
}
