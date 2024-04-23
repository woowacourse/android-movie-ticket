package woowacourse.movie.model

import woowacourse.movie.model.pricing.PricingSystem
import woowacourse.movie.model.pricing.UniformPricingSystem
import woowacourse.movie.model.screening.Screening
import java.io.Serializable
import java.time.LocalDate

data class Reservation(
    private val screening: Screening,
    private val quantity: Quantity,
    private val priceSystem: PricingSystem = UniformPricingSystem(),
) : Serializable {
    val price = priceSystem.calculatePrice(screening, quantity)

    fun getTitle(): String {
        return screening.movie.title
    }

    fun getScreeningSchedule(): LocalDate {
        return screening.schedule.date
    }

    fun getQuantity(): Int = quantity.value
}
