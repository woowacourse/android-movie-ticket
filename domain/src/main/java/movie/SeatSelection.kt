package movie

import data.PricePolicyInfo
import data.Seat
import data.SeatPosition
import movie.pricePolicy.PricePolicy
import java.time.LocalDateTime

class SeatSelection private constructor(
    private val seats: MutableMap<Seat, Boolean> = mutableMapOf(),
    private val cinemaConfigure: CinemaConfigure = CinemaConfigure,
) {
    constructor(rowSize: Int, colSize: Int) : this() {
        CinemaConfigure.seats.take(rowSize).forEachIndexed { rowIndex, row ->
            row.take(colSize).forEachIndexed { colIndex, seatClass ->
                seats[Seat(SeatPosition(rowIndex, colIndex), seatClass, false)] = false
            }
        }
    }

    fun clickSeat(seat: Seat) {
        seats[seat]?.let {
            seats[seat] = it.not()
        }
    }

    fun isAvailableByPosition(seatPosition: SeatPosition): Boolean {
        val seat = seats.keys.find { it.position == seatPosition }
        return seats[seat] ?: throw IllegalArgumentException()
    }

    fun findByPosition(seatPosition: SeatPosition): Seat {
        return seats.keys.find { it.position == seatPosition } ?: throw IllegalArgumentException()
    }

    fun getSelectedSeats(): List<Seat> {
        return seats.filter { it.value }.map { it.key }
    }

    fun getSelectedSeatsPrice(localDateTime: LocalDateTime, pricePolicy: PricePolicy): Int = seats
        .filter { it.value }
        .map { pricePolicy.calculatePrice(PricePolicyInfo(cinemaConfigure.getPrice(it.key.seatClass), localDateTime)).price }
        .sum()

    operator fun get(seat: Seat): Boolean {
        return seats[seat] ?: throw IllegalArgumentException()
    }
}
