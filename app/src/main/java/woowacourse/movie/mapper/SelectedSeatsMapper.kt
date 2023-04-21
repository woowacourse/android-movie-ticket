package woowacourse.movie.mapper

import woowacourse.movie.domain.seat.SelectedSeats
import woowacourse.movie.model.SelectedSeatsModel

fun SelectedSeats.toModel(): SelectedSeatsModel = SelectedSeatsModel(
    seats = seats.map { it.toModel() }.toSet()
)

fun SelectedSeatsModel.toDomain(): SelectedSeats = SelectedSeats(
    seats = seats.map { it.toDomain() }.toSet()
)
