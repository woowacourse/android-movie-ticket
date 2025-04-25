package woowacourse.movie.domain

enum class PriceRule(val price: Int) {
    RANK_B(10_000),
    RANK_S(15_000),
    RANK_A(12_000),
    ;

    fun price(memberCount: MemberCount): Int {
        return this.price * memberCount.value
    }
}
