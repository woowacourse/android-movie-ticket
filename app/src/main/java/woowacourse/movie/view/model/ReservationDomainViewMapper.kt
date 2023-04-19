package woowacourse.movie.view.model

import woowacourse.movie.domain.Reservation

class ReservationDomainViewMapper : DomainViewMapper<Reservation, ReservationViewModel> {
    override fun toDomain(reservationDto: ReservationViewModel): Reservation {
        return Reservation(
            MovieDomainViewMapper().toDomain(reservationDto.movie),
            ReservationDetailDomainViewMapper().toDomain(reservationDto.detail)
        )
    }

    override fun toView(reservation: Reservation): ReservationViewModel {
        return ReservationViewModel(
            MovieDomainViewMapper().toView(reservation.movie),
            ReservationDetailDomainViewMapper().toView(reservation.detail)
        )
    }
}
