package movie

import data.PricePolicyInfo
import data.Seat
import data.SeatPosition
import movie.pricePolicy.PricePolicy
import java.time.LocalDateTime

class SeatSelection(
    private val seats: MutableMap<Seat, Boolean> = mutableMapOf(),
    private val cinemaConfigure: CinemaConfigure = CinemaConfigure,
) {
    val sizeOfSelection: Int
        get() = seats.filter { it.value }.count()

    val selection: List<Seat>
        get() = seats.filter { it.value }.map { it.key }

    constructor(rowSize: Int, colSize: Int) : this() {
        CinemaConfigure.seats.take(rowSize).forEachIndexed { rowIndex, row ->
            row.take(colSize).forEachIndexed { colIndex, seatClass ->
                seats[Seat(SeatPosition(rowIndex, colIndex), seatClass, false)] = false
            }
        }
    }

    fun clickSeat(position: SeatPosition, maxQuantity: TicketQuantity) {
        if (get(position).not() && sizeOfSelection >= maxQuantity.toInt()) {
            return
        }

        val seat = findByPosition(position)
        seats[seat]?.let {
            seats[seat] = it.not()
        }
    }
    operator fun get(seatPosition: SeatPosition): Boolean {
        return seats[findByPosition(seatPosition)] ?: throw IllegalArgumentException()
    }

    private fun findByPosition(seatPosition: SeatPosition): Seat {
        return seats.keys.find { it.position == seatPosition } ?: throw IllegalArgumentException()
    }

    fun getSelectedSeatsPrice(localDateTime: LocalDateTime, pricePolicy: PricePolicy): Int = seats
        .filter { it.value }
        .map { pricePolicy.calculatePrice(PricePolicyInfo(cinemaConfigure.getPrice(it.key.seatClass), localDateTime)).price }
        .sum()
}
