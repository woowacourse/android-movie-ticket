package woowacourse.movie.policy

import java.time.LocalDateTime

abstract class Policy(val bookedDateTime: LocalDateTime) {
    abstract val defaultPayment: Int
    abstract fun cost(): Int
}
