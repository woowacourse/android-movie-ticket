package woowacourse.movie.policy

import woowacourse.movie.ticket.Ticket

class DefaultPolicy(ticket: Ticket) : Policy(ticket) {
    override val payment: Int = ticket.seat.rank.price

    override fun cost(): Int {
        return payment
    }
}
