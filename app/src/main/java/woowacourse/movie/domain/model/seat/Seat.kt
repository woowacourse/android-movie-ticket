package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class Seat(val row: Row, val column: Column, val seatRate: SeatRate) : Serializable
