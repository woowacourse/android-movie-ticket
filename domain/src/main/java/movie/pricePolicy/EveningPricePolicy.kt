package movie.pricePolicy

class EveningPricePolicy(
    private val discountPrice: Int,
    private val eveningLimit: Int = 20,
) : PricePolicy {
    init {
        require(eveningLimit in 0..23) { ERROR_EVENING_LIMIT }
    }
    override fun calculatePrice(price: PricePolicyInfo): PricePolicyInfo = when {
        isEveningMorning(price.reservationDateTime.hour) -> price.copy(price = price.price - discountPrice)
        else -> price
    }

    private fun isEveningMorning(hour: Int) = eveningLimit <= hour

    companion object {
        private const val ERROR_EVENING_LIMIT = "eveningLimit must be between 0 and 23"
    }
}
