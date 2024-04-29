package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.lang.IllegalStateException

@Parcelize
class Count(private var value: Int = INITIAL_COUNT) : Parcelable {
    val currentValue: Int
        get() = value

    init {
        require(value >= INITIAL_COUNT) { ERROR_NON_POSITIVE_NUMBER }
    }

    fun increase(): Int {
        value++
        return value
    }

    fun decrease(): Int {
        if (value <= 1) throw IllegalStateException(ERROR_NON_POSITIVE_NUMBER)
        value--
        return value
    }

    companion object {
        private const val INITIAL_COUNT = 1
        private const val MINIMUM_TICKET_COUNT = 1
        private const val ERROR_NON_POSITIVE_NUMBER = "구매 티켓은 ${MINIMUM_TICKET_COUNT}개 이상이어야 합니다."
    }
}
