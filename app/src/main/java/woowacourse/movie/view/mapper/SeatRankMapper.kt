package woowacourse.movie.view.mapper

import domain.seatPolicy.SeatRank
import woowacourse.movie.view.model.SeatRankViewModel

object SeatRankMapper : DomainViewMapper<SeatRank, SeatRankViewModel> {
    override fun toDomain(viewModel: SeatRankViewModel): SeatRank {
        return SeatRank.valueOf(viewModel.name)
    }

    override fun toView(domainModel: SeatRank): SeatRankViewModel {
        return SeatRankViewModel.valueOf(domainModel.name)
    }
}
