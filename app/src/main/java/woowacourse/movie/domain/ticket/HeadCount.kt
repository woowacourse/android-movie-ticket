package woowacourse.movie.domain.ticket

class HeadCount(
    val value: Int = 1,
) {
    init {
        require(value >= 1) { "예매 수량은 1개 이상이어야 합니다." }
    }

    operator fun plus(other: Int): HeadCount = HeadCount(value + other)

    operator fun minus(other: Int): HeadCount = HeadCount(value - other)
}
