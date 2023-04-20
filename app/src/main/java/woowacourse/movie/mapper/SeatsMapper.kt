package woowacourse.movie.mapper

import domain.Seat
import domain.Seats
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.dto.SeatsDto

fun SeatsDto.mapToSeats(): Seats {
    return Seats(this.seats.map { Seat(it.position.mapToDomain(), it.price.mapToTicketPrice()) })
}

fun Seats.mapToSeatsDto(): SeatsDto {
    return SeatsDto(this.seats.map { SeatDto(it.position.mapToUIModel(), it.price.mapToTicketPriceDto()) })
}
