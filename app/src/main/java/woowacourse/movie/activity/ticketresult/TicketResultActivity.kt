package woowacourse.movie.activity.ticketresult

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.TicketingInfo
import woowacourse.movie.util.Keys
import woowacourse.movie.util.getVersionDependentSerializableExtra
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val info: TicketingInfo? = intent.getVersionDependentSerializableExtra(Keys.INFO_KEY)

        if (info != null) {
            val title = findViewById<TextView>(R.id.text_title)
            title.text = info.title

            val playingDate = findViewById<TextView>(R.id.text_playing_date)
            playingDate.text = getString(
                R.string.date_time,
                DateTimeFormatter.ofPattern(getString(R.string.date_format)).format(info.playingDate),
                DateTimeFormatter.ofPattern(getString(R.string.time_format)).format(info.playingTime)
            )

            val count = findViewById<TextView>(R.id.text_person_count)
            count.text = getString(R.string.normal_count, info.count)

            val pricePayment = findViewById<TextView>(R.id.text_price_payment)
            pricePayment.text = getString(
                R.string.price_payment,
                DecimalFormat(getString(R.string.decimal_format)).format(info.price.price * info.count),
                info.payment
            )
        } else {
            Toast.makeText(this, "데이터가 로딩되지 않았습니다. 다시 시도해주세요.", Toast.LENGTH_LONG).show()
            finish()
        }
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
