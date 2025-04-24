package woowacourse.movie.domain

@JvmInline
value class MemberCount(
    val value: Int = MINIMUM_MEMBER_COUNT,
) {
    operator fun times(other: Int): Int {
        return value * other
    }

    fun increase(): Result<MemberCount> {
        return Result.success(MemberCount(value + 1))
    }

    fun decrease(): Result<MemberCount> {
        return if (value > MINIMUM_MEMBER_COUNT) {
            Result.success(
                MemberCount(value - 1),
            )
        } else {
            Result.failure(IllegalArgumentException("영화 예매 수는 1명이상이어야합니다."))
        }
    }

    init {
        require(value >= MINIMUM_MEMBER_COUNT) { "영화 예매 수는 1명이상이어야합니다." }
    }

    companion object {
        private const val MINIMUM_MEMBER_COUNT = 1
    }
}
