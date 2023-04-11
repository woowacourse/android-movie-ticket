package woowacourse.movie.domain

data class TicketingInfo(val name: String, val playingDate: String, val count: Int, val price: Price, val payment: String)
