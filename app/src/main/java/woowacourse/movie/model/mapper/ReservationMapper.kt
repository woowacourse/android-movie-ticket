package woowacourse.movie.model.mapper

import com.example.domain.model.Reservation
import woowacourse.movie.model.CountState
import woowacourse.movie.model.ReservationState

fun ReservationState.asDomain(): Reservation =
    Reservation(movieState.asDomain(), dateTime, countState.asDomain())

fun Reservation.asPresentation(): ReservationState =
    ReservationState(movie.asPresentation(), dateTime, CountState.from(count))
