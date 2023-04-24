package woowacourse.movie.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.domain.model.model.Rank
import com.example.domain.model.model.ReservationInfo
import com.example.domain.model.model.Seat
import com.example.domain.model.price.PriceCalculator
import woowacourse.movie.R
import woowacourse.movie.mapper.toReservationInfo
import woowacourse.movie.mapper.toSeatModel
import woowacourse.movie.model.ReservationInfoModel
import woowacourse.movie.model.SeatModel
import woowacourse.movie.model.TicketModel
import woowacourse.movie.util.customGetSerializable

class ReserveSeatActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_seat)

        val reservationInfoModel: ReservationInfoModel =
            intent.customGetSerializable(RESERVATION_INFO_KEY)
        initTicketInfoView(reservationInfoModel.title)
        initSeatViews(reservationInfoModel)
        val reserveButton = findViewById<Button>(R.id.btn_reserve)
        reserveButton.setOnClickListener(ReserveButtonListener(reservationInfoModel))
        setActionBar()
    }

    private fun initTicketInfoView(title: String) {
        InitView.initTextView(findViewById(R.id.text_title), title)
        InitView.initTextView(findViewById(R.id.text_price), "0")
    }

    private fun initSeatViews(
        reservationInfoModel: ReservationInfoModel
    ) {
        val reservationInfo: ReservationInfo = reservationInfoModel.toReservationInfo()
        val seatViews: List<Button> = getSeatViews()
        var selectCount = 0
        val priceTextView: TextView = findViewById(R.id.text_price)
        seatViews.forEachIndexed { index, button ->
            button.setOnClickListener {
                val seat = convertToSeat(index)
                val seatRank = Rank.map(seat.row)
                val seatPrice = PriceCalculator.calculate(
                    seatRank.price,
                    reservationInfo.playingDate,
                    reservationInfo.playingTime
                ).price
                selectCount = button.manageSelectedCondition(
                    priceTextView, selectCount, reservationInfo.count, seatPrice
                )
                changeReserveEnabledCondition(selectCount, reservationInfo.count)
            }
        }
    }

    private fun changeReserveEnabledCondition(selectCount: Int, goalCount: Int) {
        val reserveButton: Button = findViewById(R.id.btn_reserve)
        reserveButton.isEnabled = checkReserveAvailable(selectCount, goalCount)
    }

    private fun checkReserveAvailable(selectCount: Int, goalCount: Int) = selectCount == goalCount

    private fun Button.manageSelectedCondition(
        priceTextView: TextView,
        selectCount: Int,
        goalCount: Int,
        price: Int
    ): Int {
        val currentPrice = priceTextView.text.toString().toInt()
        if (isSelected) {
            setBackgroundColor(Color.WHITE)
            isSelected = false
            priceTextView.text = currentPrice.minus(price).toString()
            return selectCount.minus(1)
        }
        return if (checkReserveAvailable(selectCount, goalCount)) selectCount
        else {
            setBackgroundColor(getColor(R.color.selected_seat_color))
            isSelected = true
            priceTextView.text = currentPrice.plus(price).toString()
            selectCount.plus(1)
        }
    }

    private fun getSeatViews() = findViewById<TableLayout>(R.id.table_seat)
        .children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<Button>().toList()

    private fun getDialog(clickYes: () -> Unit): AlertDialog.Builder = AlertDialog.Builder(this)
        .setTitle(getString(R.string.dialog_title))
        .setMessage(getString(R.string.dialog_message))
        .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
            clickYes()
        }
        .setNegativeButton(getString(R.string.dialog_no)) { dialog, _ ->
            dialog.dismiss()
        }

    private fun convertToSeat(index: Int): Seat {
        val row = index / COLUMN_COUNT
        val column = index % COLUMN_COUNT
        return Seat(row, column)
    }

    private fun getIntentToSend(ticketModel: TicketModel): Intent {
        val intent = Intent(this, TicketResultActivity::class.java)
        intent.putExtra(TICKET_KEY, ticketModel)
        return intent
    }

    inner class ReserveButtonListener(
        private val reservationInfoModel: ReservationInfoModel
    ) : View.OnClickListener {
        override fun onClick(p0: View?) {
            val ticketModel =
                TicketModel(reservationInfoModel, getSelectedSeatPrice(), getSelectedSeats())
            val alertDialog = getDialog {
                startActivity(getIntentToSend(ticketModel))
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        private fun getSelectedSeatPrice(): Int {
            val priceTextView: TextView = findViewById(R.id.text_price)
            return priceTextView.text.toString().toInt()
        }

        private fun getSelectedSeats(): MutableList<SeatModel> {
            return getSeatViews()
                .withIndex()
                .filter { it.value.isSelected }
                .map { convertToSeat(it.index).toSeatModel() }
                .toMutableList()
        }
    }

    companion object {
        private const val RESERVATION_INFO_KEY = "reservationInfo"
        private const val TICKET_KEY = "ticket"
        const val COLUMN_COUNT = 4
    }
}
