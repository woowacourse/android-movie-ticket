package woowacourse.movie.ui.detail.view

interface TicketView {
    fun initClickListener(
        screenId: Int,
        ticketReserveListener: TicketReserveListener<Int>,
    )

    fun updateTicketCount(count: Int)

    fun ticketCount(): Int

    fun restoreTicketCount(count: Int)
}
