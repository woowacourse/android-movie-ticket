package woowacourse.movie.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.woowacourse.movie.domain.policy.DiscountDecorator
import woowacourse.movie.R
import woowacourse.movie.getParcelable
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieDate
import woowacourse.movie.model.MovieTime
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.mapper.toDomain

class TicketingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieDate = intent.getParcelable<MovieDate>(TicketingActivity.MOVIE_DATE_KEY)!!
        val movieTime = intent.getParcelable<MovieTime>(TicketingActivity.MOVIE_TIME_KEY)!!

        intent.getParcelable<Movie>(MovieListActivity.MOVIE_KEY)?.run {
            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_date).text = getString(
                R.string.book_date_time,
                movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
            )
        }

        intent.getParcelable<Ticket>(TicketingActivity.TICKET_KEY)?.run {
            findViewById<TextView>(R.id.tv_regular_count).text =
                getString(R.string.regular_count, count)
            findViewById<TextView>(R.id.tv_pay_result).text =
                getString(
                    R.string.movie_pay_result,
                    toDomain().calculateTotalPrice(
                        DiscountDecorator(movieDate.toDomain(), movieTime.toDomain())
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
