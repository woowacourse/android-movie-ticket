package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.TicketingInfo
import woowacourse.movie.util.Formatter
import woowacourse.movie.util.Keys
import woowacourse.movie.util.customGetSerializable

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val info: TicketingInfo = intent.customGetSerializable(Keys.INFO_KEY)

        val title = findViewById<TextView>(R.id.text_title)
        title.text = info.title

        val playingDate = findViewById<TextView>(R.id.text_playing_date)
        playingDate.text = getString(R.string.date_time,
            Formatter.dateFormat(info.playingDate),
            Formatter.timeFormat(info.playingTime)
        )

        val count = findViewById<TextView>(R.id.text_person_count)
        count.text = info.count.toString()

        val price = findViewById<TextView>(R.id.text_price)
        price.text = Formatter.decimalFormat(info.price.price * info.count)

        val pricePayment = findViewById<TextView>(R.id.text_price_payment)
        pricePayment.text = getString(R.string.price_payment, info.payment)
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
}
