package woowacourse.movie.model

@JvmInline
value class HeadCount(val count: Int = MIN_COUNT) {
    init {
        require(count >= MIN_COUNT) {
            "$count - 예매는 ${MIN_COUNT}장 이상 부터 가능합니다."
        }
    }

    fun canDecrease(): Boolean = count > MIN_COUNT

    fun increase(): HeadCount = HeadCount(count + INCREASE_COUNT)

    fun decrease(): HeadCount = HeadCount(count - INCREASE_COUNT)

    companion object {
        const val MIN_COUNT = 1
        private const val INCREASE_COUNT = 1
    }
}
