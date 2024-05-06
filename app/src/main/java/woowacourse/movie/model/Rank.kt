package woowacourse.movie.model

enum class Rank(private val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun calculateTotalPrice(ranks: List<Rank>): Int = ranks.sumOf { it.price }
    }
}
