package woowacourse.movie.policy

import java.time.LocalDateTime

class DefaultPolicy(bookedDateTime: LocalDateTime) : Policy(bookedDateTime) {
    override val defaultPayment: Int = 13_000

    override fun cost(): Int {
        return defaultPayment
    }
}
