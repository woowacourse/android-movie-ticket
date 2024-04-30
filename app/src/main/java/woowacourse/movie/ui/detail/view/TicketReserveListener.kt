package woowacourse.movie.ui.detail.view

interface TicketReserveListener<T> {
    fun increaseTicket()

    fun decreaseTicket()

    fun reserve(screenId: T)
}
