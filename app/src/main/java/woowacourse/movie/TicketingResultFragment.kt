package woowacourse.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import woowacourse.movie.MovieListFragment.Companion.MOVIE_KEY
import woowacourse.movie.TicketingFragment.Companion.TICKET_COUNT_KEY
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentTicketingResultBinding

class TicketingResultFragment : Fragment() {
    private var _binding: FragmentTicketingResultBinding? = null
    private val binding get() = _binding!!

    private val ticketCount: Int by lazy { arguments?.getInt(TICKET_COUNT_KEY) ?: 0 }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketingResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            getSerializable<Movie>(MOVIE_KEY)?.let {
                tvTitle.text = it.title
                tvDate.text = getString(R.string.movie_release_date, it.formattedDate)
            }
            tvRegularCount.text = getString(R.string.regular_count, ticketCount)
            tvPayResult.text =
                getString(R.string.movie_pay_result, ticketCount * 13000, "현장 결제")
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
