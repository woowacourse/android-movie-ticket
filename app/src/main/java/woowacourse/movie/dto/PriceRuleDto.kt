package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.PriceRule

@Parcelize
class PriceRuleDto private constructor(
    val tag: String,
    val price: Int,
) : Parcelable {
    init {
        require(tag in listOf(RANK_A, RANK_S, RANK_B)) { ERR_INVALID_RULE }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PriceRuleDto
        if (tag != other.tag) return false
        if (price != other.price) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * tag.hashCode() + price.hashCode()
    }

    companion object {
        const val RANK_A = "RANK_A"
        const val RANK_S = "RANK_S"
        const val RANK_B = "RANK_B"
        const val ERR_INVALID_RULE = "잘못된 규칙입니다."

        fun fromPriceRule(priceRule: PriceRule): PriceRuleDto {
            return PriceRuleDto(priceRule.name, priceRule.price)
        }
    }
}
