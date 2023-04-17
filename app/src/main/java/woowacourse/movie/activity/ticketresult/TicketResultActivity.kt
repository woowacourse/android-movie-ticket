package woowacourse.movie.activity.ticketresult

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.TicketingInfo
import woowacourse.movie.util.getSerializableExtraCompat
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val info: TicketingInfo? = intent.getSerializableExtraCompat(INFO_KEY)

        if (info == null) {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        initTitle(info.title)
        initPlayingDate(info.playingDate, info.playingTime)
        initCount(info.count)
        initPricePayment(info.price, info.count, info.payment)
    }

    private fun initTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.text_title)
        titleView.text = title
    }

    private fun initPlayingDate(playingDate: LocalDate, playingTime: LocalTime) {
        val playingDateView = findViewById<TextView>(R.id.text_playing_date)
        playingDateView.text = getString(
            R.string.date_time,
            DateTimeFormatter.ofPattern(getString(R.string.date_format)).format(playingDate),
            DateTimeFormatter.ofPattern(getString(R.string.time_format)).format(playingTime)
        )
    }

    private fun initCount(count: Int) {
        val countView = findViewById<TextView>(R.id.text_person_count)
        countView.text = getString(R.string.normal_count, count)
    }

    private fun initPricePayment(price: Price, count: Int, payment: String) {
        val pricePayment = findViewById<TextView>(R.id.text_price_payment)
        pricePayment.text = getString(
            R.string.price_payment,
            DecimalFormat(getString(R.string.decimal_format)).format(price.price * count),
            payment
        )
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
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
        const val INFO_KEY = "TICKETING_INFO"
    }
}
