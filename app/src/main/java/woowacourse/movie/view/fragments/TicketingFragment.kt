package woowacourse.movie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import woowacourse.movie.R
import woowacourse.movie.commit
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentTicketingBinding
import woowacourse.movie.domain.Ticket
import woowacourse.movie.getSerializable
import woowacourse.movie.view.fragments.MovieListFragment.Companion.MOVIE_KEY

class TicketingFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentTicketingBinding? = null
    private val binding get() = _binding!!

    private var _movieTicket: Ticket = Ticket()
    private val movieTicket get() = _movieTicket

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            getSerializable<Movie>(MOVIE_KEY)?.let {
                ivPoster.setImageResource(it.thumbnail)
                tvTitle.text = it.title
                tvDate.text = getString(R.string.movie_release_date, it.formattedDate)
                tvRunningTime.text = getString(R.string.movie_running_time, it.runningTime)
                tvIntroduce.text = it.introduce
            }
            btnMinus.setOnClickListener(this@TicketingFragment)
            btnPlus.setOnClickListener(this@TicketingFragment)
            btnTicketing.setOnClickListener(this@TicketingFragment)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_minus -> {
                _movieTicket = --_movieTicket
                binding.tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_plus -> {
                _movieTicket = ++_movieTicket
                binding.tvTicketCount.text = movieTicket.count.toString()
            }
            R.id.btn_ticketing -> {
                popUntilFirstTransaction() // 첫번째 fragment 제외하고 모두 pop

                val ticketingResultFragment = TicketingResultFragment().apply {
                    arguments = bundleOf(
                        MOVIE_KEY to this@TicketingFragment.getSerializable<Movie>(MOVIE_KEY),
                        TICKET_KEY to movieTicket
                    )
                }
                parentFragmentManager.commit {
                    add(R.id.fragment_movie, ticketingResultFragment)
                    addToBackStack(null)
                }
            }
        }
    }

    private fun popUntilFirstTransaction() {
        parentFragmentManager.popBackStack(
            MovieListFragment.FIRST_TRANSACTION,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        internal const val TICKET_KEY = "ticketCount"
    }
}
