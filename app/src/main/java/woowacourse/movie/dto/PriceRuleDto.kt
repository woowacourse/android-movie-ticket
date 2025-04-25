package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.PriceRule

@Parcelize
class PriceRuleDto private constructor(
    val tag: String,
    val price: Int,
) : Parcelable {
    companion object {
        fun fromPriceRule(priceRule: PriceRule): PriceRuleDto {
            return PriceRuleDto(priceRule.name, priceRule.price)
        }
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
}
