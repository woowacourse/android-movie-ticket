package woowacourse.movie.view.mapper

import domain.Reservation
import woowacourse.movie.view.model.ReservationViewModel

object ReservationMapper : DomainViewMapper<Reservation, ReservationViewModel> {
    override fun toDomain(reservationViewModel: ReservationViewModel): Reservation {
        return Reservation(
            MovieMapper.toDomain(reservationViewModel.movie),
            TicketsMapper.toDomain(reservationViewModel.tickets)
        )
    }

    override fun toView(reservation: Reservation): ReservationViewModel {
        return ReservationViewModel(
            MovieMapper.toView(reservation.movie),
            TicketsMapper.toView(reservation.detail)
        )
    }
}
