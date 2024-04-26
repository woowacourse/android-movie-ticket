package woowacourse.movie.model.board

@JvmInline
value class Seats(val seats: List<Seat> = emptyList()) {

    init {
        val positions = seats.map { it.position }
        require(positions.distinct().size == seats.size) { "중복된 위치에 좌석이 존재합니다." }
    }

    constructor(vararg seats: Seat) : this(seats.toList())

    val size: Int get() = seats.size

    operator fun plus(other: Seat): Seats {
        return Seats(seats + other)
    }

    operator fun plus(other: Seats): Seats = Seats(seats + other.seats)

    fun isEmpty(): Boolean = seats.isEmpty()

    fun toList(): List<Seat> = seats.toList()

    fun selectedSeats(): Seats = Seats(seats.filter { it.state == SeatState.SELECTED })

    fun totalPrice(): Long = seats.sumOf { it.price.price }

    fun toSeatMap(): Map<Position, Seat> {
        return seats.associateBy { it.position }
    }
}
