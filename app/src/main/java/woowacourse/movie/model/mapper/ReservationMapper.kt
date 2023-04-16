package woowacourse.movie.model.mapper

import com.example.domain.model.Reservation
import woowacourse.movie.model.ReservationRes

fun ReservationRes.asDomain(): Reservation =
    with(getInfo()) { Reservation(first.asDomain(), second, third) }

fun Reservation.asPresentation(): ReservationRes =
    ReservationRes.from(movie.asPresentation(), dateTime, count)
