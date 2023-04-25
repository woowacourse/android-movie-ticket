package woowacourse.movie.policy

import woowacourse.movie.ticket.Ticket

abstract class Policy(val ticket: Ticket) {
    abstract val payment: Int
    abstract fun cost(): Int
}
