package woowacourse.movie.domain.model.reservation

import java.io.Serializable

@JvmInline
value class PurchaseCount(val value: Int) : Serializable {
    init {
        require(value >= 1) { VALIDATE_PURCHASE_COUNT }
    }

    fun increase() = PurchaseCount(value + 1)

    fun canDecrease(): Boolean = value >= 2

    fun decrease() = PurchaseCount(value - 1)

    companion object {
        private const val VALIDATE_PURCHASE_COUNT = "구매 개수는 1개 이상입니다. "
    }
}
