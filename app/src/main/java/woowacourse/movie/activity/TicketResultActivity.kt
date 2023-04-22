package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.SeatModel
import woowacourse.movie.model.TicketModel
import woowacourse.movie.util.customGetSerializable

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        initTicketDataView()
        setActionBar()
    }

    private fun initTicketDataView() {
        val ticketModel: TicketModel = getReceivedIntentData()
        initTitle(ticketModel.reservationInfoModel.title)
        initPlayingDate(
            ticketModel.reservationInfoModel.playingDate,
            ticketModel.reservationInfoModel.playingTime
        )
        initCountAndSeat(ticketModel.reservationInfoModel.count, ticketModel.seats)
        initPricePayment(ticketModel.price, ticketModel.reservationInfoModel.payment)
    }

    private fun getReceivedIntentData(): TicketModel = intent.customGetSerializable(TICKET_KEY)

    private fun initTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.text_title)
        titleView.text = title
    }

    private fun initPlayingDate(playingDate: String, playingTime: String) {
        val playingDateView = findViewById<TextView>(R.id.text_playing_date)
        playingDateView.text = getString(
            R.string.date_time,
            playingDate,
            playingTime
        )
    }

    private fun initCountAndSeat(count: Int, seats: List<SeatModel>) {
        val countView = findViewById<TextView>(R.id.text_count_seat)
        countView.text = getString(
            R.string.count_seat_info,
            count,
            seats.joinToString(", ") { it.row + it.column }
        )
    }

    private fun initPricePayment(price: Int, payment: String) {
        val pricePayment = findViewById<TextView>(R.id.text_price_payment)
        pricePayment.text = getString(R.string.price_payment, price, payment)
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        private const val TICKET_KEY = "ticket"
    }
}
