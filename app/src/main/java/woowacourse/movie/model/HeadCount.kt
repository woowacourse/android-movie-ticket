package woowacourse.movie.model

class HeadCount(val count: Int) {

    init {
        require(count > 0) {
            "$count - 예매는 1장 이상 부터 가능합니다."
        }
    }

    fun canDecrease() = count > 1

    fun increase(): HeadCount = HeadCount(count + 1)

    fun decrease(): HeadCount = HeadCount(count - 1)
}