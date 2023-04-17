package com.example.domain.model

@JvmInline
value class Count(val value: Int) {
    init {
        require(value >= MIN_COUNT_RANGE) { COUNT_ERROR_MESSAGE }
    }

    operator fun minus(value: Int) = Count(this.value - value)
    operator fun plus(value: Int) = Count(this.value + value)

    companion object {
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 예약 인원 수는 1명 이상이여야 합니다."
        private const val MIN_COUNT_RANGE = 1
    }
}
