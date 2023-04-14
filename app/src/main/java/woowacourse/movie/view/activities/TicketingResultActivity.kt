package woowacourse.movie.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.Movie
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.getSerializable

class TicketingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieDate = intent.getSerializable<MovieDate>(TicketingActivity.MOVIE_DATE_KEY)!!
        val movieTime = intent.getSerializable<MovieTime>(TicketingActivity.MOVIE_TIME_KEY)!!

        intent.getSerializable<Movie>(MovieListActivity.MOVIE_KEY)?.run {
            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_date).text = getString(
                R.string.book_date_time,
                movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
            )
        }

        intent.getSerializable<Ticket>(TicketingActivity.TICKET_KEY)?.run {
            findViewById<TextView>(R.id.tv_regular_count).text =
                getString(R.string.regular_count, count)
            findViewById<TextView>(R.id.tv_pay_result).text =
                getString(
                    R.string.movie_pay_result,
                    calculateTotalPrice(
                        listOf(movieDate, movieTime)
                    ),
                    getString(R.string.on_site_payment)
                )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this@TicketingResultActivity, MovieListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
