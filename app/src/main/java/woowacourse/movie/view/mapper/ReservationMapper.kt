package woowacourse.movie.view.mapper

import domain.Reservation
import woowacourse.movie.view.model.ReservationUiModel

object ReservationMapper : DomainViewMapper<Reservation, ReservationUiModel> {
    override fun toDomain(reservationUiModel: ReservationUiModel): Reservation {
        return Reservation(
            MovieMapper.toDomain(reservationUiModel.movie),
            TicketsMapper.toDomain(reservationUiModel.tickets)
        )
    }

    override fun toUi(reservation: Reservation): ReservationUiModel {
        return ReservationUiModel(
            MovieMapper.toUi(reservation.movie),
            TicketsMapper.toUi(reservation.detail)
        )
    }
}
