package woowacourse.movie.view.mapper

import domain.TicketOffice
import woowacourse.movie.view.model.TicketOfficeViewModel

object TicketOfficeMapper : DomainViewMapper<TicketOffice, TicketOfficeViewModel> {
    override fun toDomain(viewModel: TicketOfficeViewModel): TicketOffice {
        return TicketOffice(
            tickets = TicketsMapper.toDomain(viewModel.ticketsViewModel),
            peopleCount = viewModel.ticketCount
        )
    }

    override fun toView(domainModel: TicketOffice): TicketOfficeViewModel {
        return TicketOfficeViewModel(
            ticketsViewModel = TicketsMapper.toView(domainModel.tickets),
            ticketCount = domainModel.peopleCount
        )
    }

}
