package woowacourse.movie.model

import woowacourse.movie.domain.price.Price
import java.text.DecimalFormat

fun Price.toPresentation(): PriceModel {
    return PriceModel(DecimalFormat("#,###").format(price))
}
