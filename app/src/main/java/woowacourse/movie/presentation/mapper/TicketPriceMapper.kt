package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.movie.DomainTicketPrice
import woowacourse.movie.presentation.model.TicketPrice

fun TicketPrice.toDomain(): DomainTicketPrice =
    DomainTicketPrice(amount)

fun DomainTicketPrice.toPresentation(): TicketPrice =
    TicketPrice(amount)
