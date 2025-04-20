package woowacourse.movie.domain.model

class HeadCountManager {
    var count: Int = 1
        private set

    fun increase() {
        count++
    }

    fun decrease() {
        if (count > 1) count--
    }

    fun restore(saveCount: Int) {
        if (saveCount > 1) count = saveCount
    }
}
