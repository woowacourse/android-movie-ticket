package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Price

fun mapToPrice(price: PriceModel): Price = Price(price.amount)

fun mapToPriceModel(price: Price): PriceModel = PriceModel(price.amount)

@Parcelize
data class PriceModel(val amount: Int) : Parcelable
