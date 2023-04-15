package movie.pricePolicy

class EarlyMorningPricePolicy(
    private val discountPrice: Int,
    private val morningLimit: Int = 11,
) : PricePolicy {
    init {
        require(morningLimit in 0..23) { ERROR_MORNING_LIMIT }
    }
    override fun invoke(price: PricePolicyInfo): PricePolicyInfo = when {
        isEarlyMorning(price.reservationDateTime.hour) -> price.copy(price = price.price - discountPrice)
        else -> price
    }

    private fun isEarlyMorning(hour: Int) = hour <= morningLimit

    companion object {
        private const val ERROR_MORNING_LIMIT = "morningLimit must be between 0 and 23"
    }
}
