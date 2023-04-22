package woowacourse.movie.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.databinding.ActivityMoviesBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.util.Mock

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMoviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        val movieListView: LinearLayout = binding.root
        setContentView(movieListView)

        initMovieListView(binding.moviesListView)
    }

    private fun initMovieListView(movieListView: RecyclerView) {
        val movieModels: List<MovieModel> = Mock.getMovieModels()
        movieListView.adapter = MoviesAdapter(movieModels)
    }
}
