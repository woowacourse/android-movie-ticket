package woowacourse.movie.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.ui.getSerializable
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PeopleCountModel
import woowacourse.movie.ui.model.TicketTimeModel
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
        intent.getSerializable<MovieTicketModel>("ticket")?.let {
            findViewById<TextView>(R.id.ticket_title).text = it.title
            findViewById<TextView>(R.id.ticket_date).text = it.time.format()
            findViewById<TextView>(R.id.ticket_people_count).text = it.peopleCount.format()
            findViewById<TextView>(R.id.ticket_price).text = it.getPriceWithUnit()
        }
    }

    private fun TicketTimeModel.format(): String =
        dateTime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))

    private fun PeopleCountModel.format(): String = getString(R.string.people_count, count)

    private fun MovieTicketModel.getPriceWithUnit(): String = getString(R.string.price, DecimalFormat("#,###").format(price))
}
