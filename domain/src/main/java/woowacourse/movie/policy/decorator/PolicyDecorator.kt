package woowacourse.movie.policy.decorator

import woowacourse.movie.policy.Policy

abstract class PolicyDecorator(policy: Policy) : Policy(policy.ticket) {
    override val payment: Int = policy.cost()
}
