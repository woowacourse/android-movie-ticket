package woowacourse.movie.policy.decorator

import woowacourse.movie.policy.Policy

abstract class PolicyDecorator(policy: Policy) : Policy(policy.bookedDateTime) {
    override val defaultPayment: Int = policy.cost()
}
