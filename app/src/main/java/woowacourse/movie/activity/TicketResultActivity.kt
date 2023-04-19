package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.price.Price
import com.example.domain.model.toUI
import woowacourse.movie.R
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.formatter.DecimalFormatter
import woowacourse.movie.formatter.TimeFormatter
import woowacourse.movie.util.customGetSerializable
import java.time.LocalDate
import java.time.LocalTime

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        initTicketDataView()
        setActionBar()
    }

    private fun initTicketDataView() {
        val info: com.example.domain.model.TicketingInfo = intent.customGetSerializable(INFO_KEY)
        initTitle(info.title)
        initPlayingDate(info.playingDate, info.playingTime)
        initCount(info.count)
        initPrice(info.price, info.count)
        initPricePayment(info.payment)
    }

    private fun initTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.text_title)
        titleView.text = title
    }

    private fun initPlayingDate(playingDate: LocalDate, playingTime: LocalTime) {
        val playingDateView = findViewById<TextView>(R.id.text_playing_date)
        playingDateView.text = getString(
            R.string.date_time,
            DateFormatter.formatToString(playingDate),
            TimeFormatter.formatToString(playingTime)
        )
    }

    private fun initCount(count: Int) {
        val countView = findViewById<TextView>(R.id.text_person_count)
        countView.text = count.toString()
    }

    private fun initPrice(price: Price, count: Int) {
        val priceView = findViewById<TextView>(R.id.text_price)
        priceView.text = DecimalFormatter.formatToString(price.price * count)
    }

    private fun initPricePayment(payment: com.example.domain.model.Payment) {
        val pricePayment = findViewById<TextView>(R.id.text_price_payment)
        pricePayment.text = getString(R.string.price_payment, payment.toUI())
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
