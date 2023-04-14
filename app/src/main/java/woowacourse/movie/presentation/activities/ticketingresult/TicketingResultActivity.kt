package woowacourse.movie.presentation.activities.ticketingresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityTicketingResultBinding
import woowacourse.movie.domain.model.movie.MovieDate
import woowacourse.movie.domain.model.movie.MovieTime
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_DATE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_TIME_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.TICKET_KEY
import woowacourse.movie.presentation.extensions.getParcelableExtraCompat
import woowacourse.movie.presentation.model.Movie

class TicketingResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showTicketingResult()
    }

    private fun showTicketingResult() {
        with(binding) {
            val movieDate = intent.getParcelableExtraCompat<MovieDate>(MOVIE_DATE_KEY)!!
            val movieTime = intent.getParcelableExtraCompat<MovieTime>(MOVIE_TIME_KEY)!!
            intent.getParcelableExtraCompat<Movie>(MOVIE_KEY)?.run {
                tvTitle.text = title
                tvDate.text = getString(
                    R.string.book_date_time,
                    movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
                )
            }
            intent.getParcelableExtraCompat<Ticket>(TICKET_KEY)?.let {
                tvRegularCount.text = getString(R.string.regular_count, it.count)
                tvPayResult.text =
                    getString(
                        R.string.movie_pay_result,
                        it.calculateTotalPrice(listOf(movieDate, movieTime)),
                        getString(R.string.on_site_payment)
                    )
            }
        }
    }
}
