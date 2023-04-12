package woowacourse.movie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import woowacourse.movie.R
import woowacourse.movie.commit
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentMovieListBinding
import woowacourse.movie.view.adapter.MovieListAdapter

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private var _listAdapter: MovieListAdapter? = null
    private val listAdapter get() = _listAdapter!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _listAdapter =
            MovieListAdapter(
                Movie.provideDummy(),
                object : MovieListAdapter.OnBookClickListener {
                    override fun onClick(item: Movie) {
                        val ticketingFragment =
                            TicketingFragment().apply { arguments = bundleOf(MOVIE_KEY to item) }
                        parentFragmentManager.commit {
                            add(R.id.fragment_movie, ticketingFragment)
                            addToBackStack(FIRST_TRANSACTION)
                        }
                    }
                }
            )
        binding.lvMovies.adapter = listAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _listAdapter = null
    }

    companion object {
        internal const val FIRST_TRANSACTION = "FirstTransaction"
        internal const val MOVIE_KEY = "movie"
    }
}
