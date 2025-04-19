package woowacourse.movie.view.booking

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.TicketType
import woowacourse.movie.view.StringFormatter.thousandFormat

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bookedTicket: BookedTicket = intent.getSerializableExtra("bookedTicket") as BookedTicket
        val price = bookedTicket.peopleCount.ticketPrice(TicketType.GENERAL)
        val priceFormat = thousandFormat(price)

        findViewById<TextView>(R.id.tv_title).text = bookedTicket.movieName
        findViewById<TextView>(R.id.tv_release_date).text = bookedTicket.time
        findViewById<TextView>(R.id.tv_price).text =
            getString(R.string.text_on_site_payment).format(priceFormat)
        findViewById<TextView>(R.id.tv_people_count).text =
            getString(R.string.text_general_people_count).format(bookedTicket.peopleCount.count)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
}
