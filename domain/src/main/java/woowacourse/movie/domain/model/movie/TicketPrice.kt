package woowacourse.movie.domain.model.movie

import woowacourse.movie.domain.model.discount.policy.DiscountPolicy

typealias DomainTicketPrice = TicketPrice

@JvmInline
value class TicketPrice(val amount: Int = DEFAULT_TICKET_PRICE) {
    fun applyDiscountPolicy(
        vararg discountPolicies: DiscountPolicy,
    ): TicketPrice =
        TicketPrice(
            discountPolicies.fold(amount) { discounted, discountPolicy ->
                discountPolicy.discount(discounted)
            }
        )

    operator fun times(operand: Int): TicketPrice = TicketPrice(amount * operand)

    operator fun plus(operand: TicketPrice): TicketPrice = TicketPrice(amount + operand.amount)

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
