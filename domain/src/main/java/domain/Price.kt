package domain

data class Price(val value: Int = DEFAULT_PRICE) {
    companion object {
        private const val DEFAULT_PRICE = 13000
    }
}
