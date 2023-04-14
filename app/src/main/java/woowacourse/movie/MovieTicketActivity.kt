package woowacourse.movie

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.domain.TicketTime
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
        dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))

    private fun PeopleCount.format(): String = "일반 ${count}명"

    private fun MovieTicket.getPriceWithUnit(): String =
        "${DecimalFormat("#,###").format(getPrice())}원 (현장 결제)"
}
