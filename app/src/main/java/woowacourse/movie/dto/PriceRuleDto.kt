package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.rules.PriceRule

@Parcelize
data class PriceRuleDto(
    val tag: PriceRuleUiTag,
    val price: Int,
) : Parcelable {
    companion object {
        fun fromPriceRule(priceRule: PriceRule): PriceRuleDto {
            return PriceRuleDto(PriceRuleUiTag.find(priceRule), priceRule.price)
        }
    }
}
