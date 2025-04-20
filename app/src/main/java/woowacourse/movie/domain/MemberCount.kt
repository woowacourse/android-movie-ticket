package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class MemberCount(
    val value: Int
) : Parcelable {
    init {
        require(value >= MINIMUM_NUMBER_OF_PEOPLE) {"영화 예매 수는 1명이상이어야합니다."}
    }

    fun calculateTicketPrices(): Int {
        return value * TICKET_PRICE
    }

    companion object {
        const val MINIMUM_NUMBER_OF_PEOPLE = 1
        private const val TICKET_PRICE = 13_000
    }
}
