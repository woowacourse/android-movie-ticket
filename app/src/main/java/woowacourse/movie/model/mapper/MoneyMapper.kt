package woowacourse.movie.model.mapper

import com.example.domain.model.Money
import woowacourse.movie.model.MoneyState

fun MoneyState.asDomain() = Money(price)
fun Money.asPresentation() = MoneyState(value)
