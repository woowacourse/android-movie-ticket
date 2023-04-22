package woowacourse.movie.view.mapper
import domain.Tickets
import woowacourse.movie.view.model.TicketsViewModel

object TicketsMapper : DomainViewMapper<Tickets, TicketsViewModel> {
    override fun toView(domainModel: Tickets): TicketsViewModel {
        return TicketsViewModel(domainModel.list.map { TicketMapper.toView(it) })
    }

    override fun toDomain(viewModel: TicketsViewModel): Tickets {
        return Tickets(viewModel.list.map { TicketMapper.toDomain(it) })
    }
}
