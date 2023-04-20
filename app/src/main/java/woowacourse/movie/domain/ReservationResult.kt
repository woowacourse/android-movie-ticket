package woowacourse.movie.domain

import java.time.LocalDateTime

data class ReservationResult(val selectedDateTime: LocalDateTime, val audienceCount: Int) {

    val fee = DiscountPolicy.getDiscountedFee(selectedDateTime, Money(TICKET_PRICE)) * audienceCount

    init {
        require(audienceCount in MIN_AUDIENCE_COUNT..MAX_AUDIENCE_COUNT) { AUDIENCE_COUNT_RANGE_ERROR }
    }


    companion object {
        const val MIN_AUDIENCE_COUNT = 1
        const val MAX_AUDIENCE_COUNT = 200
        private const val TICKET_PRICE = 13_000
        private const val AUDIENCE_COUNT_RANGE_ERROR =
            "[ERROR] 예매 인원은 최소 ${MIN_AUDIENCE_COUNT}명 이상 최대 ${MAX_AUDIENCE_COUNT}명 이하여야 합니다."
    }
}
