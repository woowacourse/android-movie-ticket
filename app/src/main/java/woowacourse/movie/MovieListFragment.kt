package woowacourse.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.FragmentMovieListBinding

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
                        // Fragment 전환
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
}
