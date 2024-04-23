package woowacourse.movie.ui.detail

interface ITicketViewHolder {
    fun updateTicketCount(count: Int)

    fun ticketCount(): Int

    fun restoreTicketCount(count: Int)
}
