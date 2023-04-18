package woowacourse.movie.dto

import woowacourse.movie.domain.Reservation

class ReservationDtoConverter : DtoConverter<Reservation, ReservationDto> {
    override fun convertDtoToModel(reservationDto: ReservationDto): Reservation {
        return Reservation(
            MovieDtoConverter().convertDtoToModel(reservationDto.movie),
            ReservationDetailDtoConverter().convertDtoToModel(reservationDto.detail)
        )
    }

    override fun convertModelToDto(reservation: Reservation): ReservationDto {
        return ReservationDto(
            MovieDtoConverter().convertModelToDto(reservation.movie),
            ReservationDetailDtoConverter().convertModelToDto(reservation.detail)
        )
    }
}
