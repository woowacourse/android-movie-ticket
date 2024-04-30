package woowacourse.movie.model

import woowacourse.movie.model.ticketing.BookingSeat

class BoxOffice private constructor(
    val count: Int,
    seats: List<BookingSeat>,
) {
    val totalPrice: Int
        get() = seats.sumOf { seat -> seat.seatClass.price }

    var seats: List<BookingSeat> = seats
        private set

    val isSubmitAvailable: Boolean
        get() = seats.size == count

    fun updateSeats(newSeats: List<BookingSeat>): Result<Unit> {
        if (newSeats.size > count) return Result.Error(ERROR_OVER_COUNT)
        seats = newSeats
        return Result.Success(Unit)
    }

    companion object {
        private const val ERROR_OVER_COUNT = "예약 가능 인원 수를 초과하였습니다."

        fun of(
            numberOfTickets: Count,
            seats: List<BookingSeat>,
        ): BoxOffice =
            BoxOffice(
                count = numberOfTickets.currentValue,
                seats = seats,
            )
    }
}
