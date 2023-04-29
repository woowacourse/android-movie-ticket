package com.example.domain.model

@JvmInline
value class Money(val value: Int) {
    init {
        require(value >= MIN_MONEY_RANGE) { MONEY_ERROR_MESSAGE }
    }

    operator fun times(other: Float): Money {
        val result = (value * other).toInt()
        return Money(result)
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }

    companion object {
        private const val MONEY_ERROR_MESSAGE = "[ERROR} 예매 금액은 0원보다 작을 수 없습니다"
        private const val MIN_MONEY_RANGE = 0
    }
}
