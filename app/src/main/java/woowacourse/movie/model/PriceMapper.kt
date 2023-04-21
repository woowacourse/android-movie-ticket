package woowacourse.movie.model

import woowacourse.movie.domain.price.Price

fun Price.toPresentation(): Int {
    return price
}
