package woowacourse.movie.domain.discountPolicy

import woowacourse.movie.domain.Price
import woowacourse.movie.domain.Ticket

class DisCountPolicies(list: List<DiscountPolicy>) {
    private val _list = list.toMutableList()
    private val list = _list.toList()

    fun addPolicy(discountPolicy: DiscountPolicy) {
        _list.add(discountPolicy)
    }

    fun deletePolicy(discountPolicy: DiscountPolicy) {
        _list.remove(discountPolicy)
    }

    fun calculate(
        ticket: Ticket,
        price: Price
    ): Price {
        var presentPrice = price
        for (item in list) {
            presentPrice = item.discount(ticket, presentPrice)
        }
        return presentPrice
    }
}
