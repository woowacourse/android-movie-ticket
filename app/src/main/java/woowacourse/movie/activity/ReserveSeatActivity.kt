package woowacourse.movie.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

class ReserveSeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_seat)

        val reservationInfoModel: ReservationInfoModel = getReceivedIntentData()
        initTitle(reservationInfoModel.title)
        initPrice()
        val reserveButton = findViewById<Button>(R.id.btn_reserve)
        initSeatViews(reservationInfoModel)
        reserveButton.setOnClickListener(ReserveButtonListener(reservationInfoModel))
        setActionBar()
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
                val seatRank = Rank.map(seat.row + 1)
                val seatPrice = PriceCalculator.calculate(
                    seatRank.price,
                    reservationInfo.playingDate,
                    reservationInfo.playingTime
                ).price
                selectCount = button.manageSelectedCondition(
                    priceTextView, selectCount, seatPrice
                )
                changeReserveEnabledCondition(selectCount, reservationInfo.count)
            }
        }
    }

    private fun changeReserveEnabledCondition(selectCount: Int, goalCount: Int) {
        val reserveButton: Button = findViewById(R.id.btn_reserve)
        reserveButton.isEnabled = selectCount == goalCount
    }

    private fun Button.manageSelectedCondition(
        priceTextView: TextView,
        selectCount: Int,
        price: Int
    ): Int {
        val currentPrice = priceTextView.text.toString().toInt()
        return if (isSelected) {
            setBackgroundColor(Color.WHITE)
            isSelected = false
            priceTextView.text = currentPrice.minus(price).toString()
            selectCount.minus(1)
        } else {
            setBackgroundColor(Color.parseColor(SELECTED_SEAT_COLOR))
            isSelected = true
            priceTextView.text = currentPrice.plus(price).toString()
            selectCount.plus(1)
        }
    }

    private fun initPrice() {
        val priceTextView = findViewById<TextView>(R.id.text_price)
        priceTextView.text = "0"
    }

    private fun initTitle(titleText: String) {
        val title = findViewById<TextView>(R.id.text_title)
        title.text = titleText
    }

    private fun getReceivedIntentData(): ReservationInfoModel =
        intent.customGetSerializable(RESERVATION_INFO_KEY)

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

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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
            val selectedSeats = mutableListOf<SeatModel>()
            getSeatViews().forEachIndexed { index, button ->
                if (button.isSelected) {
                    val seat = convertToSeat(index)
                    selectedSeats.add(seat.toSeatModel())
                }
            }
            return selectedSeats
        }
    }

    companion object {
        private const val RESERVATION_INFO_KEY = "reservationInfo"
        private const val TICKET_KEY = "ticket"
        const val COLUMN_COUNT = 4
        private const val SELECTED_SEAT_COLOR = "#FAFF00"
    }
}
