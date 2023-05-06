package woowacourse.movie.domain.discount

import woowacourse.movie.repository.DiscountPolicyRepository
import java.time.LocalDateTime

object DiscountApplicator {

    fun applyDiscount(screeningDateTime: LocalDateTime, initFee: Money): Money {
        val discountPolicies = DiscountPolicyRepository.findAll().sortedBy { it.priority }
        return discountPolicies.fold(initFee) { fee, discountPolicy ->
            discountPolicy.getDiscountedFee(screeningDateTime, fee)
        }
    }
}
