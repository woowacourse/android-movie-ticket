package woowacourse.movie.dto

import java.io.Serializable

data class SeatDto(val position: Int, val price: TicketPriceDto) : Serializable
