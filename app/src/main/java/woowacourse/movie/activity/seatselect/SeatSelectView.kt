package woowacourse.movie.activity.seatselect

import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.activity.InjectedModelListener
import woowacourse.movie.domain.price.Price
import woowacourse.movie.domain.system.PriceSystem
import woowacourse.movie.domain.system.SeatSelectSystem
import woowacourse.movie.domain.system.SelectResult
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.model.PriceModel
import woowacourse.movie.model.TicketModel
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.Theater
import java.time.LocalDateTime

class SeatSelectView(
    private val viewGroup: ViewGroup,
    private val seatSystem: SeatSelectSystem,
    private val priceSystem: PriceSystem,
    private val clickListener: InjectedModelListener<TicketModel>,
) {
    private val seatViews: List<TextView> = viewGroup.findViewById<TableLayout>(R.id.layout_seats)
        .children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<TextView>().toList()

    private val titleView = viewGroup.findViewById<TextView>(R.id.text_title)
    private val priceView = viewGroup.findViewById<TextView>(R.id.text_price)
    private val nextButton = viewGroup.findViewById<Button>(R.id.btn_next)

    private var price: Price = Price(0)

    fun set(title: String, dateTime: LocalDateTime) {
        setSeatViews()
        setTitle(title)
        setPrice(price.toPresentation())
        setNextButton(title, dateTime)
    }

    private fun setTitle(title: String) {
        titleView.text = title
    }

    private fun setNextButton(title: String, dateTime: LocalDateTime) {
        nextButton.setOnClickListener {
            clickListener.onClick(Ticket(title, dateTime, seatSystem.seats, price).toPresentation())
        }
    }

    private fun setPrice(price: PriceModel) {
        priceView.text = viewGroup.context.getString(R.string.price, price.price)
    }

    private fun setSeatViews() {
        seatViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                val (row, col) = indexToRowCol(index)
                val result = seatSystem.select(row, col)
                when (result) {
                    is SelectResult.Success.Selection -> {
                        textView.setBackgroundColor(textView.context.getColor(R.color.select_seat))
                        if (result.isSelectAll) {
                            nextButton.isEnabled = true
                        }
                    }
                    is SelectResult.Success.Deselection -> {
                        textView.setBackgroundColor(textView.context.getColor(R.color.white))
                        nextButton.isEnabled = false
                        setPrice(result.seatPrice.toPresentation())
                    }
                    is SelectResult.MaxSelection -> {
                        Toast.makeText(
                            viewGroup.context,
                            SELECT_ALL_SEAT_MESSAGE,
                            Toast.LENGTH_LONG,
                        )
                            .show()
                    }
                    is SelectResult.WrongInput -> {
                        Toast.makeText(
                            viewGroup.context,
                            SELECT_WRONG_SEAT_MESSAGE,
                            Toast.LENGTH_LONG,
                        )
                            .show()
                    }
                }
                val newPrice = priceSystem.getCurrentPrice(price, result)
                price = newPrice
                setPrice(newPrice.toPresentation())
            }
        }
    }

    private fun indexToRowCol(index: Int): Pair<Int, Int> = Pair((index) / Theater.col, (index) % Theater.col)

    companion object {
        private const val SELECT_ALL_SEAT_MESSAGE = "좌석을 이미 다 선택하셨습니다."
        private const val SELECT_WRONG_SEAT_MESSAGE = "잘못된 접근입니다."
    }
}
