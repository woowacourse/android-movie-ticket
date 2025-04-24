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
}
