package woowacourse.movie.dto

import java.io.Serializable

data class SeatDto(val position: PositionDto, val price: TicketPriceDto) : Serializable
