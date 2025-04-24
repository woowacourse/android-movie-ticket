package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class TicketCount(
    val value: Int
) : Parcelable {
    init {
        require(value >= MINIMUM_NUMBER_OF_PEOPLE) {ERROR_PEOPLE_OVER_ONE}
    }

    fun calculateTicketPrices(): Int {
        return value * TICKET_PRICE
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_PEOPLE = 1
        private const val ERROR_PEOPLE_OVER_ONE = "영화 예매 수는 1명이상이어야합니다."
        private const val TICKET_PRICE = 13_000
    }
}
