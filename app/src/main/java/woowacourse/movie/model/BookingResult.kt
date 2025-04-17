package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingResult(
    val title: String,
    private var _headCount: Int,
    private var _selectedDate: String,
    private var _selectedTime: String,
) : Parcelable {
    val headCount get() = _headCount
    val selectedDate get() = _selectedDate
    val selectedTime get() = _selectedTime

    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLANK_MESSAGE }
    }

    fun calculateAmount(): Int {
        return TICKET_PRICE * headCount
    }

    fun plusHeadCount() {
        _headCount++
    }

    fun minusHeadCount() {
        _headCount--
    }

    fun updateDate(date: String) {
        _selectedDate = date
    }

    fun updateTime(time: String) {
        _selectedTime = time
    }

    fun isHeadCountValid(): Boolean {
        return headCount > 0
    }

    companion object {
        private const val TICKET_PRICE = 13_000
        private const val ERROR_TITLE_BLANK_MESSAGE = "예매한 영화 제목은 비어 있을 수 없다"
    }
}
