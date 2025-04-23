package woowacourse.movie.domain

enum class PriceRule(val price: Int) {
    NORMAL(13_000),
    ;

    fun price(memberCount: MemberCount): Int {
        return this.price * memberCount.value
    }
}
