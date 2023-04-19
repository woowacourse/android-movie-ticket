package woowacourse.movie.presentation.activities.ticketingresult

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityTicketingResultBinding
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.activities.ticketing.SeatPickerActivity
import woowacourse.movie.presentation.activities.ticketing.SeatPickerActivity.Companion.PICKED_SEATS_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_DATE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_TIME_KEY
import woowacourse.movie.presentation.extensions.getParcelableCompat
import woowacourse.movie.presentation.extensions.showBackButton
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.PickedSeats
import woowacourse.movie.presentation.model.Ticket
import woowacourse.movie.presentation.model.TicketPrice
import woowacourse.movie.presentation.model.movieitem.Movie

class TicketingResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBackButton()
        showTicketingResult()
    }

    private fun showTicketingResult() {
        showMovieInformation()
        showPaymentPrice()
    }

    private fun showMovieInformation() {
        val pickedSeats = intent.getParcelableCompat<PickedSeats>(PICKED_SEATS_KEY)!!
        val movieDate = intent.getParcelableCompat<MovieDate>(MOVIE_DATE_KEY)!!
        val movieTime = intent.getParcelableCompat<MovieTime>(MOVIE_TIME_KEY)!!

        intent.getParcelableCompat<Movie>(MOVIE_KEY)?.run {
            binding.tvTitle.text = title
            binding.tvSeats?.text = pickedSeats.sorted().toString()
            binding.tvDate.text = getString(
                R.string.book_date_time,
                movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
            )
        }
    }

    private fun showPaymentPrice() {
        val ticket = intent.getParcelableCompat<Ticket>(TicketingActivity.TICKET_KEY)!!
        val ticketPrice =
            intent.getParcelableCompat<TicketPrice>(SeatPickerActivity.TOTAL_TICKET_PRICE_KEY)!!

        binding.tvRegularCount.text = getString(R.string.regular_count, ticket.count)
        binding.tvPayResult.text = getString(
            R.string.movie_pay_result,
            ticketPrice.amount,
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
