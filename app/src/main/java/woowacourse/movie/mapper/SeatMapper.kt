package woowacourse.movie.mapper

import domain.Seat
import woowacourse.movie.dto.SeatDto

fun SeatDto.mapToSeat(): Seat {
    return Seat(this.row.mapToDomain(), this.col.mapToDomain())
}

fun Seat.mapToSeatDto(): SeatDto {
    return SeatDto(this.row.mapToUIModel(), this.col.mapToUIModel())
}
