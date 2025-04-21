package woowacourse.movie.domain

import java.time.LocalTime

enum class PurchaseType(val cancelTime: LocalTime) {
    DEFAULT(LocalTime.of(0, 15)),
}
