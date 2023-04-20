package woowacourse.movie.mapper

import domain.Seat
import woowacourse.movie.dto.SeatDto

fun SeatDto.mapToSeat(): Seat {
    return Seat(this.position.mapToDomain(), this.price.mapToTicketPrice())
}

fun Seat.mapToSeatDto(): SeatDto {
    return SeatDto(this.position.mapToUIModel(), this.price.mapToTicketPriceDto())
}
