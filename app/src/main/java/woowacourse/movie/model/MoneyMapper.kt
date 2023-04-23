package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class MoneyMapper(val value: Int) : Parcelable {
    init {
        require(value > MIN_MONEY_RANGE) { MONEY_ERROR_MESSAGE }
    }

    operator fun times(other: Float): MoneyMapper {
        val result = (value * other).toInt()
        return MoneyMapper(result)
    }

    operator fun times(other: CountMapper): MoneyMapper {
        return MoneyMapper(value * other.value)
    }

    operator fun minus(other: MoneyMapper): MoneyMapper {
        return MoneyMapper(value - other.value)
    }

    companion object {
        private const val MONEY_ERROR_MESSAGE = "[ERROR} 예매 금액은 0원보다 커야 합니다."
        private const val MIN_MONEY_RANGE = 0
    }
}
