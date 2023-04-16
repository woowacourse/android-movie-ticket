package woowacourse.movie.ui.activity

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.ui.dto.MovieTicket
import woowacourse.movie.ui.dto.PeopleCount
import woowacourse.movie.ui.dto.TicketTime
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTicketInfo()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setContentView(R.layout.activity_movie_ticket)
        super.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTicketInfo() {
        val ticket = getTicketFromIntent()
        findViewById<TextView>(R.id.ticket_title).text = ticket.title
        findViewById<TextView>(R.id.ticket_date).text = ticket.time.format()
        findViewById<TextView>(R.id.ticket_people_count).text = ticket.peopleCount.format()
        findViewById<TextView>(R.id.ticket_price).text = ticket.getPriceWithUnit()
    }

    @Suppress("DEPRECATION")
    private fun getTicketFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("ticket", MovieTicket::class.java)
    } else {
        intent.getSerializableExtra("ticket")
    } as MovieTicket

    private fun TicketTime.format(): String =
        dateTime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))

    private fun PeopleCount.format(): String = getString(R.string.people_count, count)

    private fun MovieTicket.getPriceWithUnit(): String = getString(R.string.price, DecimalFormat("#,###").format(price))
}
