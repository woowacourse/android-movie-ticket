package movie.discountpolicy

import java.time.LocalDate
import java.time.LocalTime

class NormalDiscountPolicy(
    private val date: LocalDate,
    private val time: LocalTime,
) : DiscountPolicy {

    private val policies = listOf(
        MovieDayDiscountPolicy(date),
        EveningDiscountPolicy(time),
        EarlyMorningDiscountPolicy(time),
    )

    override fun getDiscountPrice(price: Int): Int {
        return policies.fold(price) { total, policy ->
            total - policy.getDiscountPrice(price)
        }
    }
}
