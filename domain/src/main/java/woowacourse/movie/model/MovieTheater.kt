package woowacourse.movie.model

class MovieTheater(
    private val rowRate: Map<SeatRate, List<Int>>,
    private val colLength: Int,
    val defaultPrice: Int = 0,
) {
    fun seats(): List<Seat> =
        rowRate.flatMap { (seatRate, rateRows) ->
            rateRows.flatMap { row ->
                (0 until colLength).map { col ->
                    Seat(seatRate, row - 1, col)
                }
            }
        }
}
