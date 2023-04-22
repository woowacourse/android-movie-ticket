package woowacourse.movie.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
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

        val seatViews: List<Button> = findViewById<TableLayout>(R.id.table_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>().toList()

        val reservationInfoModel: ReservationInfoModel =
            intent.customGetSerializable(RESERVATION_INFO_KEY)
        val reservationInfo: ReservationInfo = reservationInfoModel.toReservationInfo()

        val title = findViewById<TextView>(R.id.text_title)
        title.text = reservationInfoModel.title

        val priceTextView = findViewById<TextView>(R.id.text_price)
        priceTextView.text = "0"

        var selectCount = 0

        val reserveButton = findViewById<Button>(R.id.btn_reserve)
        seatViews.forEachIndexed { index, button ->
            button.setOnClickListener {
                val seat = calculateRowColumn(index)
                val rank = Rank.map(seat.row + 1)
                val rankPrice = rank.price
                val finalPrice = PriceCalculator.calculate(
                    rankPrice,
                    reservationInfo.playingDate,
                    reservationInfo.playingTime
                )
                val price: Int
                if (button.isSelected) {
                    button.setBackgroundColor(Color.WHITE)
                    button.isSelected = false
                    selectCount--
                    price = priceTextView.text.toString().toInt() - finalPrice.price
                } else {
                    button.setBackgroundColor(Color.parseColor("#FAFF00"))
                    button.isSelected = true
                    selectCount++
                    price = priceTextView.text.toString().toInt() + finalPrice.price
                }

                priceTextView.text = price.toString()
                reserveButton.isEnabled = selectCount == reservationInfoModel.count
            }
        }

        reserveButton.setOnClickListener {
            val selectedSeats = mutableListOf<SeatModel>()
            seatViews.forEachIndexed { index, button ->
                if (button.isSelected) {
                    val seat = calculateRowColumn(index)
                    selectedSeats.add(seat.toSeatModel())
                }
            }
            val price = priceTextView.text.toString().toInt()
            val ticketModel = TicketModel(reservationInfoModel, price, selectedSeats)

            val alertDialog = getDialog {
                val intent = Intent(this, TicketResultActivity::class.java)
                intent.putExtra(TICKET_KEY, ticketModel)
                startActivity(intent)
            }

            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        setActionBar()
    }

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

    private fun calculateRowColumn(index: Int): Seat {
        val row = index / COLUMN_COUNT
        val column = index % COLUMN_COUNT
        return Seat(row, column)
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

    companion object {
        private const val RESERVATION_INFO_KEY = "reservationInfo"
        private const val TICKET_KEY = "ticket"
        const val COLUMN_COUNT = 4
    }
}
