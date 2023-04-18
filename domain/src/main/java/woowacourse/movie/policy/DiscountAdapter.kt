package woowacourse.movie.policy

import woowacourse.movie.policy.decorator.DatePolicy
import woowacourse.movie.policy.decorator.TimePolicy
import java.time.LocalDateTime

class DiscountAdapter {
    fun getPayment(bookedDateTime: LocalDateTime): Int {
        var policy: Policy = DefaultPolicy(bookedDateTime)
        policy = DatePolicy(policy)
        policy = TimePolicy(policy)
        return policy.cost()
    }
}
