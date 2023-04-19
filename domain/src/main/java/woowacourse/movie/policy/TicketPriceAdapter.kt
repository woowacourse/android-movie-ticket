package woowacourse.movie.policy

import woowacourse.movie.policy.decorator.DatePolicy
import woowacourse.movie.policy.decorator.TimePolicy
import woowacourse.movie.ticket.Ticket

class TicketPriceAdapter {
    fun getPayment(ticket: Ticket): Int {
        var policy: Policy = DefaultPolicy(ticket)
        policy = DatePolicy(policy)
        policy = TimePolicy(policy)
        return policy.cost()
    }
}
