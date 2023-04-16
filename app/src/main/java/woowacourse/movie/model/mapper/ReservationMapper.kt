package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.ReservationDomain
import woowacourse.movie.model.Reservation

fun Reservation.toDomain(): ReservationDomain =
    ReservationDomain(movie.toDomain(), dateTime, ticket.toDomain())

fun ReservationDomain.toPresentation(): Reservation =
    Reservation(movie.toPresentation(), dateTime, ticket.toPresentation())
