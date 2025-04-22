package woowacourse.movie.domain.model

class HeadCount(
    private var count: Int = 1,
) {
    init {
        if (count < 1) {
            count = 1
        }
    }

    fun getCount(): Int = count

    fun increase() {
        count++
    }

    fun decrease() {
        count = (count - 1).coerceAtLeast(1)
    }
}
