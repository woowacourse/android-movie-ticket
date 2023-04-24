package woowacourse.movie.view.mapper

import domain.Seat
import domain.seatPolicy.SeatPolicies
import woowacourse.movie.view.model.SeatUiModel

object SeatMapper : DomainViewMapper<Seat, SeatUiModel> {
    override fun toDomain(seatUiModel: SeatUiModel): Seat {
        return Seat(
            SeatUiModel.toNumber(seatUiModel.row),
            seatUiModel.col,
            SeatPolicies()
        )
    }

    override fun toUi(domainModel: Seat): SeatUiModel {
        return SeatUiModel(
            SeatUiModel.toChar(domainModel.row),
            domainModel.col
        )
    }

}
