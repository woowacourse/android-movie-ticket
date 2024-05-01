package woowacourse.movie.model.pricing

interface PricePolicy {
    fun getPrice(): Int
}
