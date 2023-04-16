package woowacourse.movie.presentation.activities.ticketingresult

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityTicketingResultBinding
import woowacourse.movie.domain.model.discount.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.model.discount.policy.MovieTimeDiscountPolicy
import woowacourse.movie.domain.model.movie.TicketPrice
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_DATE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_TIME_KEY
import woowacourse.movie.presentation.extensions.getParcelableExtraCompat
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.model.Movie
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.Ticket

class TicketingResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBackButtonOnToolbar()
        showTicketingResult()
    }

    private fun showBackButtonOnToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showTicketingResult() {
        val movieDate = intent.getParcelableExtraCompat<MovieDate>(MOVIE_DATE_KEY)!!
        val movieTime = intent.getParcelableExtraCompat<MovieTime>(MOVIE_TIME_KEY)!!
        val ticket = intent.getParcelableExtraCompat<Ticket>(TicketingActivity.TICKET_KEY)!!

        showMovieInformation(movieDate, movieTime)
        showPaymentPrice(ticket, movieDate, movieTime)
    }

    private fun showMovieInformation(
        movieDate: MovieDate,
        movieTime: MovieTime,
    ) {
        intent.getParcelableExtraCompat<Movie>(MOVIE_KEY)?.run {
            binding.tvTitle.text = title
            binding.tvDate.text = getString(
                R.string.book_date_time,
                movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
            )
        }
    }

    private fun showPaymentPrice(
        ticket: Ticket,
        movieDate: MovieDate,
        movieTime: MovieTime,
    ) {
        val totalTicketsPrice = TicketPrice().applyDiscountPolicy(
            MovieDayDiscountPolicy(movieDate.toDomain()),
            MovieTimeDiscountPolicy(movieTime.toDomain()),
        ) * ticket.count

        binding.tvRegularCount.text = getString(R.string.regular_count, ticket.count)
        binding.tvPayResult.text = getString(
            R.string.movie_pay_result,
            totalTicketsPrice.amount,
            getString(R.string.on_site_payment)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
