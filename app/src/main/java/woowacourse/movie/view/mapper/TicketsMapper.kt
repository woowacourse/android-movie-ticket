package woowacourse.movie.view.mapper
import domain.Tickets
import woowacourse.movie.view.model.TicketsUiModel

object TicketsMapper : DomainViewMapper<Tickets, TicketsUiModel> {
    override fun toUi(domainModel: Tickets): TicketsUiModel {
        return TicketsUiModel(domainModel.list.map { TicketMapper.toUi(it) })
    }

    override fun toDomain(ticketsUiModel: TicketsUiModel): Tickets {
        return Tickets(ticketsUiModel.list.map { TicketMapper.toDomain(it) })
    }
}
