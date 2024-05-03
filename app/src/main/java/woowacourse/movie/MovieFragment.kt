package woowacourse.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import woowacourse.movie.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private var title: String? = null
    var isShowed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString("title")
        Log.d("james", "Title: $title")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        binding.movie = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMove.setOnClickListener {
            parentFragmentManager.commit {
                val fragment = TicketFragment()
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putInt("price", 15000)
                setFragmentResult("requestKey", bundle)

                setReorderingAllowed(true)
                replace(R.id.fragment_container_view, fragment)
                addToBackStack(null)
            }
        }

        binding.btnHide.setOnClickListener {
            isShowed = !isShowed
            binding.invalidateAll()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
