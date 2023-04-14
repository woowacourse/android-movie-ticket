package woowacourse.movie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import woowacourse.movie.R
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentTicketingResultBinding
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.extensions.getParcelableCompat
import woowacourse.movie.view.fragments.MovieListFragment.Companion.MOVIE_KEY
import woowacourse.movie.view.fragments.TicketingFragment.Companion.MOVIE_DATE_KEY
import woowacourse.movie.view.fragments.TicketingFragment.Companion.MOVIE_TIME_KEY
import woowacourse.movie.view.fragments.TicketingFragment.Companion.TICKET_KEY

class TicketingResultFragment : Fragment() {
    private var _binding: FragmentTicketingResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTicketingResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTicketingResult()
    }

    private fun showTicketingResult() {
        with(binding) {
            val movieDate = getParcelableCompat<MovieDate>(MOVIE_DATE_KEY)!!
            val movieTime = getParcelableCompat<MovieTime>(MOVIE_TIME_KEY)!!
            getParcelableCompat<Movie>(MOVIE_KEY)?.run {
                tvTitle.text = title
                tvDate.text = getString(
                    R.string.book_date_time,
                    movieDate.year, movieDate.month, movieDate.day, movieTime.hour, movieTime.min
                )
            }
            getParcelableCompat<Ticket>(TICKET_KEY)?.let {
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
