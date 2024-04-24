package woowacourse.movie.model

import woowacourse.movie.model.pricing.PricingSystem
import woowacourse.movie.model.pricing.UniformPricingSystem
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

data class Reservation(
    val id: Long,
    val screening: Screening,
    val quantity: Quantity,
    private val priceSystem: PricingSystem = UniformPricingSystem(),
) {
    val price
        get() = priceSystem.calculatePrice(screening, quantity)

    fun getTitle(): String {
        return screening.movie.title
    }

    fun getScreeningSchedule(): LocalDate {
        return screening.schedule.date
    }

    fun getQuantity(): Int = quantity.value
}
