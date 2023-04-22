package woowacourse.movie.view.mapper

import domain.Seat
import domain.seatPolicy.SeatPolicies
import woowacourse.movie.view.model.SeatRowViewModel
import woowacourse.movie.view.model.SeatViewModel

object SeatMapper : DomainViewMapper<Seat, SeatViewModel> {
    override fun toDomain(viewModel: SeatViewModel): Seat {
        return Seat(
            SeatRowViewModel.toNumber(viewModel.row),
            viewModel.col,
            SeatPolicies()
        )
    }

    override fun toView(domainModel: Seat): SeatViewModel {
        return SeatViewModel(
            SeatRowViewModel.numberToSeatRow(domainModel.row),
            domainModel.col
        )
    }

}
