package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.TicketingInfo
import java.text.DecimalFormat

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val info: TicketingInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("ticketingInfo", TicketingInfo::class.java)
                ?: throw IllegalArgumentException("오류가 발생했습니다.")
        } else {
            intent.getSerializableExtra("ticketingInfo") as TicketingInfo
        }

        val title = findViewById<TextView>(R.id.text_title)
        val playingDate = findViewById<TextView>(R.id.text_playing_date)
        val count = findViewById<TextView>(R.id.text_person_count)
        val price = findViewById<TextView>(R.id.text_price)
        val pricePayment = findViewById<TextView>(R.id.text_price_payment)

        title.text = info.title
        playingDate.text = info.playingDate
        count.text = info.count.toString()
        val totalPrice = DecimalFormat("#,###").format(info.price.price * info.count)
        price.text = totalPrice.toString()
        pricePayment.text = getText(R.string.price_payment).toString().format(info.payment)
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
