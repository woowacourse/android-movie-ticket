package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingActivity.Companion.KEY_BOOKING_DATE_TIME
import woowacourse.movie.BookingActivity.Companion.KEY_BOOKING_MOVIE_TITLE
import woowacourse.movie.BookingActivity.Companion.KEY_BOOKING_PEOPLE_COUNT
import woowacourse.movie.BookingActivity.Companion.KEY_BOOKING_TICKET_PRICE
import woowacourse.movie.StringFormatter.thousandFormat

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

        val title = intent.getStringExtra(KEY_BOOKING_MOVIE_TITLE)
        val time = intent.getStringExtra(KEY_BOOKING_DATE_TIME)
        val peopleCount = intent.getIntExtra(KEY_BOOKING_PEOPLE_COUNT, DEFAULT_PEOPLE_COUNT)
        val price = intent.getIntExtra(KEY_BOOKING_TICKET_PRICE, DEFAULT_TICKET_PRICE)
        val priceFormat = thousandFormat(price)

        findViewById<TextView>(R.id.tv_title).text = title
        findViewById<TextView>(R.id.tv_release_date).text = time
        findViewById<TextView>(R.id.tv_price).text =
            getString(R.string.text_on_site_payment).format(priceFormat)
        findViewById<TextView>(R.id.tv_people_count).text =
            getString(R.string.text_general_people_count).format(peopleCount)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val DEFAULT_PEOPLE_COUNT = 0
        private const val DEFAULT_TICKET_PRICE = 0
    }
}
