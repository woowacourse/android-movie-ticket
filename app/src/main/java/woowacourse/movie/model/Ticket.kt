package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class Ticket(
    count: Int = DEFAULT_AMOUNT,
    screeningInfo: Pair<LocalDate, LocalTime> = DEFAULT_SCREEN_INFO
) : Serializable {
    var count: Int = count
        private set

    var screeningInfo = screeningInfo
        private set

    init {
        require(count > 0) {
            ERROR_AMOUNT_LESS_THAN_ONE
        }
    }

    fun subCount() {
        if (count > 1) {
            count--
        }
    }

    fun addCount() {
        count++
    }

    fun count(): Int = count

    fun restoreCount(count: Int) {
        this.count = count
    }

    companion object {
        const val DEFAULT_AMOUNT: Int = 1
        val DEFAULT_SCREEN_INFO: Pair<LocalDate, LocalTime> =
            LocalDate.now() to LocalTime.now()
        const val ERROR_AMOUNT_LESS_THAN_ONE = "amount 값은 1 이상이어야 합니다."
    }
}
