package woowacourse.movie.view.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.utils.StringFormatter.thousandFormat
import woowacourse.movie.utils.intentSerializable

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyWindowInsets()
        setViews()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setViews() {
        val bookedTicket: BookedTicket =
            intent.intentSerializable(EXTRA_BOOKED_TICKET, BookedTicket::class.java) as BookedTicket
        val price: Int = bookedTicket.totalPrice()
        val priceFormat: String = thousandFormat(price)

        findViewById<TextView>(R.id.tv_title).text = bookedTicket.movieName
        findViewById<TextView>(R.id.tv_release_date).text = bookedTicket.time
        findViewById<TextView>(R.id.tv_price).text =
            getString(R.string.text_on_site_payment).format(priceFormat)
        findViewById<TextView>(R.id.tv_headcount).text =
            getString(R.string.text_general_people_count).format(bookedTicket.headcount.count)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    companion object {
        fun newIntent(
            context: Context,
            bookedTicket: BookedTicket,
        ): Intent =
            Intent(context, BookingActivity::class.java).apply {
                putExtra(EXTRA_BOOKED_TICKET, bookedTicket)
            }

        private const val EXTRA_BOOKED_TICKET = "bookedTicket"
    }
}
