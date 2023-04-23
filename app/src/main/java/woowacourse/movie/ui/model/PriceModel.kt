package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Price

fun PriceModel.mapToPrice() = Price(amount)

fun Price.mapToPriceModel() = PriceModel(amount)

@Parcelize
data class PriceModel(val amount: Int) : Parcelable
