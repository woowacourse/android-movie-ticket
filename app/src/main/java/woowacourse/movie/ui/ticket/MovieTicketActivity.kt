package woowacourse.movie.ui.ticket

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.MovieTicketModel
import woowacourse.movie.ui.const.KEY_TICKET
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.showToast
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
        val ticket: MovieTicket? = intent.getSerializableExtraCompat<MovieTicketModel>(KEY_TICKET)?.toDomain()

        if (ticket == null) {
            showToast(getString(R.string.error_loading))
            finish()
        }

        ticket?.let { it ->
            findViewById<TextView>(R.id.ticket_title).text = it.title
            findViewById<TextView>(R.id.ticket_date).text = it.time.format()
            findViewById<TextView>(R.id.ticket_people_count).text =
                getString(R.string.people_count, it.peopleCount.count)
            findViewById<TextView>(R.id.ticket_price).text =
                getString(R.string.price_with_unit, DecimalFormat("#,###").format(it.getPrice()))
        }
    }

    private fun LocalDateTime.format(): String =
        format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))
}
