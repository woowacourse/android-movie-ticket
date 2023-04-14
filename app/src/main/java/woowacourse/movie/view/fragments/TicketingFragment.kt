package woowacourse.movie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import woowacourse.movie.R
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentTicketingBinding
import woowacourse.movie.domain.MovieDate
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.commit
import woowacourse.movie.utils.extensions.getParcelableCompat
import woowacourse.movie.utils.extensions.showToast
import woowacourse.movie.view.fragments.MovieListFragment.Companion.MOVIE_KEY

class TicketingFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentTicketingBinding? = null
    private val binding get() = _binding!!

    private var _movieTicket: Ticket = Ticket()
    private val movieTicket get() = _movieTicket

    private val movieDates: List<MovieDate> by lazy {
        getParcelableCompat<Movie>(MOVIE_KEY)?.run {
            MovieDate.releaseDates(
                from = startDate,
                to = endDate
            )
        }
            ?: emptyList()
    }
    private val _movieTimes = mutableListOf<MovieTime>()
    private val movieTimes: List<MovieTime> get() = _movieTimes

    private val movieDateAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item,
            movieDates.map { getString(R.string.book_date, it.year, it.month, it.day) }
        )
    }
    private val movieTimeAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mutableListOf())
    }
    private var _selectedDate: MovieDate? = null
    private val selectedDate: MovieDate? get() = _selectedDate

    private var _selectedTime: MovieTime? = null
    private val selectedTime: MovieTime? get() = _selectedTime

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        restoreState(savedInstanceState)
        _binding = FragmentTicketingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            showMovieIntroduce()
            setSpinnerConfig()
            setClickListener()
        }
    }

    private fun FragmentTicketingBinding.setClickListener() {
        btnMinus.setOnClickListener(this@TicketingFragment)
        btnPlus.setOnClickListener(this@TicketingFragment)
        btnTicketing.setOnClickListener(this@TicketingFragment)
    }

    private fun FragmentTicketingBinding.setSpinnerConfig() {
        setSpinnerAdapter()
        setSpinnerListener()
    }

    private fun FragmentTicketingBinding.setSpinnerAdapter() {
        spinnerMovieDate.adapter = movieDateAdapter
        spinnerMovieTime.adapter = movieTimeAdapter
        movieDateAdapter.setNotifyOnChange(true)
        movieTimeAdapter.setNotifyOnChange(true)
    }

    private fun FragmentTicketingBinding.setSpinnerListener() {
        spinnerMovieTime.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long,
            ) {
                _selectedTime = movieTimes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerMovieDate.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemView: View?,
                pos: Int,
                id: Long,
            ) {
                _selectedDate = movieDates[pos]
                _movieTimes.clear()
                selectedDate?.run {
                    _movieTimes.addAll(
                        MovieTime.runningTimes(
                            isWeekend(),
                            isToday()
                        )
                    )
                }
                movieTimeAdapter.clear()
                movieTimeAdapter.addAll(
                    movieTimes.map { getString(R.string.book_time, it.hour, it.min) }
                )
                _selectedTime = movieTimes.firstOrNull()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun FragmentTicketingBinding.showMovieIntroduce() {
        getParcelableCompat<Movie>(MOVIE_KEY)?.run {
            ivPoster.setImageResource(thumbnail)
            tvTitle.text = title
            tvDate.text = getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            tvRunningTime.text = getString(R.string.movie_running_time, runningTime)
            tvIntroduce.text = introduce
        }
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            _selectedDate = getParcelableCompat(SELECTED_DATE_STATE_KEY)
            _selectedTime = getParcelableCompat(SELECTED_TIME_STATE_KEY)
            _movieTicket = getParcelableCompat(TICKET_COUNT_STATE_KEY)!!
        }
        selectedDate?.run { _movieTimes.addAll(MovieTime.runningTimes(isWeekend(), isToday())) }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.tvTicketCount.text = movieTicket.count.toString()
        val storedDateIndex = movieDates.indexOfFirst { it == selectedDate }
        binding.spinnerMovieDate.setSelection(storedDateIndex)
        val storedTimeIndex = movieTimes.indexOfFirst { it == selectedTime }
        binding.spinnerMovieTime.setSelection(storedTimeIndex)
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
                if (selectedDate == null || selectedTime == null) {
                    showToast(getString(R.string.select_date_and_time))
                    return
                }

                popUntilFirstTransaction() // 첫번째 fragment 제외하고 모두 pop
                val ticketingResultFragment = TicketingResultFragment().apply {
                    arguments = bundleOf(
                        MOVIE_KEY to this@TicketingFragment.getParcelableCompat<Movie>(MOVIE_KEY),
                        TICKET_KEY to movieTicket,
                        MOVIE_DATE_KEY to selectedDate,
                        MOVIE_TIME_KEY to selectedTime,
                    )
                }
                parentFragmentManager.commit {
                    add(
                        R.id.fragment_movie,
                        ticketingResultFragment,
                        TicketingResultFragment::class.java.name
                    )
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TICKET_COUNT_STATE_KEY, movieTicket)
        outState.putParcelable(SELECTED_DATE_STATE_KEY, selectedDate)
        outState.putParcelable(SELECTED_TIME_STATE_KEY, selectedTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        internal const val TICKET_KEY = "ticketCount"
        internal const val MOVIE_DATE_KEY = "movieDate"
        internal const val MOVIE_TIME_KEY = "movieTime"

        internal const val SELECTED_DATE_STATE_KEY = "selectedDate"
        internal const val SELECTED_TIME_STATE_KEY = "selectedTime"
        internal const val TICKET_COUNT_STATE_KEY = "ticketCountState"
    }
}
