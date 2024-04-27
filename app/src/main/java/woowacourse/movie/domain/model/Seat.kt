package woowacourse.movie.domain.model

data class Seat(val row: String, val number: Int, val seatGrade: SeatGrade){
    override fun toString(): String {
        return "$row$number"
    }
}
