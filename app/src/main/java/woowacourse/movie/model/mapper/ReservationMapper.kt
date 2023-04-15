package woowacourse.movie.model.mapper

import com.example.domain.model.Reservation
import woowacourse.movie.model.ReservationInfo

fun ReservationInfo.asDomain(): Reservation =
    with(getInfo()) { Reservation(first.asDomain(), second, third) }

fun Reservation.asPresentation(): ReservationInfo =
    ReservationInfo.from(movie.asPresentation(), dateTime, count)
