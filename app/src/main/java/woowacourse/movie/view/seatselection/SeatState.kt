package woowacourse.movie.view.seatselection

import android.content.Context
import android.widget.TextView
import com.example.domain.DiscountPrice
import com.example.domain.seat.Seat
import woowacourse.movie.R
import java.time.LocalDateTime

class SeatState(
    date: LocalDateTime,
    private var _priceNum: Int = 0,
    private var _countPeople: Int = 0
) {
    val priceNum: Int
        get() = _priceNum
    val countPeople: Int
        get() = _countPeople
    private val discountPrice = DiscountPrice(date)
    private val seats = Seat.makeSeats()

    fun setSeatState(index: Int, view: TextView, context: Context) {
        if (view.isSelected) {
            view.isSelected = false
            view.setBackgroundColor(context.getColor(R.color.white))
            _priceNum -= discountPrice.calculateTotalPrice(seats[index].rank.price)
            _countPeople -= 1
        } else {
            view.isSelected = true
            view.setBackgroundColor(context.getColor(R.color.seat_select_state))
            _priceNum += discountPrice.calculateTotalPrice(seats[index].rank.price)
            _countPeople += 1
        }
    }
}
