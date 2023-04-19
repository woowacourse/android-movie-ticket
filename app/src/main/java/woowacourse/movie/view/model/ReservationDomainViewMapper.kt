package woowacourse.movie.view.model

import domain.Reservation

class ReservationDomainViewMapper : DomainViewMapper<domain.Reservation, ReservationViewModel> {
    override fun toDomain(reservationDto: ReservationViewModel): domain.Reservation {
        return domain.Reservation(
            MovieDomainViewMapper().toDomain(reservationDto.movie),
            ReservationDetailDomainViewMapper().toDomain(reservationDto.detail)
        )
    }

    override fun toView(reservation: domain.Reservation): ReservationViewModel {
        return ReservationViewModel(
            MovieDomainViewMapper().toView(reservation.movie),
            ReservationDetailDomainViewMapper().toView(reservation.detail)
        )
    }
}
