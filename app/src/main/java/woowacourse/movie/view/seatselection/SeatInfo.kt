package woowacourse.movie.view.seatselection

import android.widget.TextView
import com.example.domain.DiscountPrice
import com.example.domain.seat.SeatPosition
import com.example.domain.seat.Seats
import com.example.domain.seat.indexToPosition
import java.time.LocalDateTime

class SeatInfo(
    date: LocalDateTime,
    private var _priceNum: Int = 0,
    private var _countPeople: Int = 0
) {
    val priceNum: Int
        get() = _priceNum
    val countPeople: Int
        get() = _countPeople
    private val discountPrice = DiscountPrice(date)
    private val seats = Seats.makeSeats()

    fun setSeatState(view: TextView, index: Int): Boolean {
        val seatRank = seats[indexToPosition(index)] ?: throw IllegalArgumentException()
        return if (view.isSelected) {
            view.isSelected = false
            _priceNum -= discountPrice.calculateTotalPrice(seatRank.price)
            _countPeople -= 1
            false
        } else {
            view.isSelected = true
            _priceNum += discountPrice.calculateTotalPrice(seatRank.price)
            _countPeople += 1
            true
        }
    }

    fun getSelectedSeats(view: List<TextView>): List<SeatPosition> {
        val result = mutableListOf<SeatPosition>()
        view.forEachIndexed { index, textView ->
            if (textView.isSelected)
                result.add(indexToPosition(index))
        }
        return result
    }
}
