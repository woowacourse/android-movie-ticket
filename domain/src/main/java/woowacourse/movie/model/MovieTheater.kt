package woowacourse.movie.model

class MovieTheater(val rowRate: Map<SeatRate, List<Int>>, val colLength: Int) {

    fun seats(): List<Seat> = rowRate.flatMap { (seatRate, rateRows) ->
        rateRows.flatMap { row ->
            (0 until colLength).map { col ->
                Seat(seatRate, row, col)
            }
        }
    }
}
