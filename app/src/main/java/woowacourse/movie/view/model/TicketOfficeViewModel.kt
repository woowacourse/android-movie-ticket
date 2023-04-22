package woowacourse.movie.view.model

import java.io.Serializable

class TicketOfficeViewModel(val ticketsViewModel: TicketsViewModel, val ticketCount: Int) : Serializable,ViewModel
