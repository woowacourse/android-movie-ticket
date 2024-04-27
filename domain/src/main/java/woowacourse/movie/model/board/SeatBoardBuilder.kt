package woowacourse.movie.model.board

import woowacourse.movie.model.HeadCount

@DslMarker
annotation class BoardDsl

@BoardDsl
interface DslBuilder<T> {

    fun build(): T
}

fun buildSeatBoard(block: SeatBoardBuilder.() -> Unit): SeatBoard {
    return SeatBoardBuilder().apply(block).build()
}


class SeatBoardBuilder : DslBuilder<SeatBoard> {
    private var boardSize: BoardSize = DEFAULT_BOARD_SIZE
    private var headCount: HeadCount = DEFAULT_HEAD_COUNT
    private var totalSeats: Seats = Seats()
    private var gradePolicy: SeatGradePolicy = DEFAULT_SEAT_GRADE_POLICY
    private var pricePolicy: SeatPricePolicy = DEFAULT_SEAT_PRICE_POLICY
    private var reservedPositions: Set<Position> = emptySet()
    private var bannedPositions: Set<Position> = emptySet()

    fun size(width: Int, height: Int) {
        boardSize = BoardSize(width, height)
    }

    fun headCount(count: HeadCount) {
        headCount = count
    }

    fun gradePolicy(policy: SeatGradePolicy) {
        gradePolicy = policy
    }

    fun pricePolicy(policy: SeatPricePolicy) {
        pricePolicy = policy
    }

    fun totalSeats(savedSeats: List<Seat>) {
        validateSeatsInBoardSize(Seats(savedSeats))
        totalSeats = Seats(savedSeats)
    }

    fun reservedSeatPositions(positions: Set<Position>) {
        this.reservedPositions = positions
    }

    fun bannedPositions(positions: Set<Position>) {
        this.bannedPositions = positions
    }

    override fun build(): SeatBoard {
        if (totalSeats.isEmpty().not()) {
            validateSeatsInBoardSize(totalSeats)
            return SeatBoard(headCount, totalSeats)
        }
        validateSamePosition()
        validatePositionsInBoardSize(reservedPositions)
        validatePositionsInBoardSize(bannedPositions)
        totalSeats = createSeats()
        return SeatBoard(headCount, totalSeats, boardSize)
    }

    private fun createSeats(): Seats {
        val widthRange = 0 until boardSize.width
        val heightRange = 0 until boardSize.height

        val initSeats = heightRange.map { x ->
            widthRange.map { y ->
                val position = Position(x, y)
                val grade = gradePolicy.grade(position)
                val price = pricePolicy.price(grade)
                Seat(position, price, grade, SeatState.EMPTY)
            }.toMutableList()
        }.toMutableList()
        bannedPositions.forEach { (x, y) ->
            initSeats[x][y] = initSeats[x][y].copy(state = SeatState.BANNED)
        }
        reservedPositions.forEach { (x, y) ->
            initSeats[x][y] = initSeats[x][y].copy(state = SeatState.RESERVED)
        }
        return initSeats.flatten().let(::Seats)
    }

    private fun validateSamePosition() {
        val intersectPosition = reservedPositions intersect bannedPositions
        require(intersectPosition.isEmpty()) { "예약된 좌석과 금지된 좌석이 중복되었습니다. 중복된 좌석 : $intersectPosition" }
    }

    private fun validateSeatsInBoardSize(seats: Seats) {
        val seatPositions = seats.seats.map { it.position }.toSet()
        validatePositionsInBoardSize(seatPositions)
    }

    private fun validatePositionsInBoardSize(positions: Set<Position>) {
        val inValidPositions = positions.filterNot(boardSize::isInBounds)
        require(inValidPositions.isEmpty()) {
            "좌석의 위치가 보드 크기를 벗어났습니다. 보드 크기 : $boardSize, 위치 : $inValidPositions"
        }
    }

    companion object {
        private val DEFAULT_BOARD_SIZE = BoardSize(4, 5)
        private val DEFAULT_HEAD_COUNT = HeadCount(1)
        private val DEFAULT_SEAT_GRADE_POLICY: SeatGradePolicy = DefaultSeatGradePolicy()
        private val DEFAULT_SEAT_PRICE_POLICY: SeatPricePolicy = DefaultSeatPricePolicy()
    }
}
