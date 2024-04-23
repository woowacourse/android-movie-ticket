package woowacourse.movie.presentation.model

sealed class MessageType {
    data class TicketMaxCountMessage(val count: Int) : MessageType()

    data class TicketMinCountMessage(val count: Int) : MessageType()

    data class AllSeatsSelectedMessage(val count: Int) : MessageType()

    data object ReservationSuccessMessage : MessageType()
}
