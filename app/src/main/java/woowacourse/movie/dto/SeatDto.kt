package woowacourse.movie.dto

import domain.Position
import java.io.Serializable

data class SeatDto(val position: Position, val price: TicketPriceDto) : Serializable
