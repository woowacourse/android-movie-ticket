package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.price.Price
import woowacourse.movie.R
import woowacourse.movie.formatter.DecimalFormatter
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
        val ticket: TicketModel = intent.customGetSerializable(INFO_KEY)
        initTitle(ticket.title)
        initPlayingDate(ticket.playingDate, ticket.playingTime)
        initCount(ticket.count)
        initPrice(ticket.price, ticket.count)
        initPricePayment(ticket.payment)
    }

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

    private fun initCount(count: Int) {
        val countView = findViewById<TextView>(R.id.text_person_count)
        countView.text = count.toString()
    }

    private fun initPrice(price: Price, count: Int) {
        val priceView = findViewById<TextView>(R.id.text_price)
        val decimalFormat = "#,###"
        priceView.text = DecimalFormatter.formatToString(price.price * count, decimalFormat)
    }

    private fun initPricePayment(payment: String) {
        val pricePayment = findViewById<TextView>(R.id.text_price_payment)
        pricePayment.text = getString(R.string.price_payment, payment)
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
        private const val INFO_KEY = "ticketingInfo"
    }
}
