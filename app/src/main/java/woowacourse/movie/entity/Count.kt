package woowacourse.movie.entity

@JvmInline
value class Count(val value: Int) : java.io.Serializable {
    init {
        require(value > 0) { COUNT_ERROR_MESSAGE }
    }

    companion object {
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 예약 인원 수는 1명 이상이여야 합니다."
    }
}
