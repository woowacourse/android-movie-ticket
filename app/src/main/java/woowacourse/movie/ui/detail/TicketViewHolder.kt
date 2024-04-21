package woowacourse.movie.ui.detail

interface TicketViewHolder {
    fun updateTicketCount(count: Int)
    fun ticketCount(): Int
    fun restoreTicketCount(count: Int)
}