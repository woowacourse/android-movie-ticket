package woowacourse.movie.model.mapper

import com.example.domain.model.Reservation
import woowacourse.movie.model.ReservationState

fun ReservationState.asDomain(): Reservation =
    with(getInfo()) { Reservation(first.asDomain(), second, third) }

fun Reservation.asPresentation(): ReservationState =
    ReservationState.from(movie.asPresentation(), dateTime, count)
