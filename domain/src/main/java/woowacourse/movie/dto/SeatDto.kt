package woowacourse.movie.dto

import woowacourse.movie.domain.Seat

data class SeatDto(val row: Int, val column: Int, val seatClass: String) {

    companion object {
        fun from(seat: Seat): SeatDto {
            return SeatDto(seat.point.row, seat.point.column, seat.seatClass.name)
        }
    }
}
