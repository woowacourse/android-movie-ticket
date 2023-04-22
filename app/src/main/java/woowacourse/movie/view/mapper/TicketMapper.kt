package woowacourse.movie.view.mapper

import domain.Ticket
import domain.discountPolicy.DisCountPolicies
import woowacourse.movie.view.model.TicketViewModel

object TicketMapper : DomainViewMapper<Ticket, TicketViewModel> {
    override fun toView(domainModel: Ticket): TicketViewModel {
        return TicketViewModel(
            date = domainModel.date,
            seat = SeatMapper.toView(domainModel.seat)
        )
    }

    override fun toDomain(viewModel: TicketViewModel): Ticket {
        return Ticket(
            viewModel.date,
            SeatMapper.toDomain(viewModel.seat),
            DisCountPolicies()
        )
    }
}
