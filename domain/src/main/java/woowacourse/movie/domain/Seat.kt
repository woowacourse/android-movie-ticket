package woowacourse.movie.domain

data class Seat(val point: Point, val seatClass: SeatClass) {

    constructor(row: Int, column: Int, seatClass: SeatClass) : this(Point(row, column), seatClass)
}
