package woowacourse.movie.ui.detail

interface TicketView {
    fun initClickListener(screenId: Int)

    fun updateTicketCount(count: Int)

    fun ticketCount(): Int

    fun restoreTicketCount(count: Int)
}
