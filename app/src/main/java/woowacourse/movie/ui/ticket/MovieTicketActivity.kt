package woowacourse.movie.ui.ticket

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.MovieTicketModel
import woowacourse.movie.ui.seat.SeatSelectionActivity
import woowacourse.movie.utils.failLoadingData
import woowacourse.movie.utils.getSerializableExtraCompat
import java.text.DecimalFormat
import java.time.LocalDateTime
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
        Log.d("TicketActivity", "set!")
        val ticket: MovieTicketModel =
            intent.getSerializableExtraCompat(SeatSelectionActivity.KEY_TICKET)
                ?: return failLoadingData()

        findViewById<TextView>(R.id.ticket_title).text = ticket.title
        findViewById<TextView>(R.id.ticket_date).text = ticket.time.format()
        findViewById<TextView>(R.id.ticket_people_count).text =
            getString(R.string.people_count, ticket.peopleCount.count)
        findViewById<TextView>(R.id.ticket_seats).text =
            getString(R.string.seats_with_separator, ticket.seats)
        findViewById<TextView>(R.id.ticket_price).text =
            getString(
                R.string.price_with_payment,
                DecimalFormat("#,###").format(ticket.seats.toDomain().getAllPrice(ticket.time))
            )
    }

    private fun LocalDateTime.format(): String =
        format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))
}
