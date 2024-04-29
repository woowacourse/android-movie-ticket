package woowacourse.movie.domain.model

data class MovieSeat(
    val seatRow: String,
    val seatColumn: Int,
    val seatType: SeatType,
){
    fun getRowIndex(): Int{
        return seatRow[0].code - MIN_ROW.code
    }

    fun getColumnIndex(): Int{
        return seatColumn-1
    }

    companion object {
        const val MIN_ROW = 'A'
    }
}
