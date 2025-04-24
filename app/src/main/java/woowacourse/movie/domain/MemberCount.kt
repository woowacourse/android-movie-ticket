package woowacourse.movie.domain

@JvmInline
value class MemberCount(
    val value: Int,
) {
    operator fun times(other: Int): Int {
        return value * other
    }

    init {
        require(value >= 1) { "영화 예매 수는 1명이상이어야합니다." }
    }
}
