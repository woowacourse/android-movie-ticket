package woowacourse.movie.activity.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import woowacourse.movie.R
import woowacourse.movie.activity.reservation.ReservationActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.dto.MovieListData
import woowacourse.movie.global.ServiceLocator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var movieDto: List<MovieListData>

    override fun initMovieDto(movies: List<MovieListData>) {
        movieDto = movies
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val presenter = ServiceLocator.mainPresenter(this)
        presenter.initMovieDto()

        binding.movies.layoutManager = LinearLayoutManager(this)
        binding.movies.adapter =
            MovieListAdapter { movieDto ->
                val intent = ReservationActivity.Companion.newIntent(this, movieDto)
                startActivity(intent)
            }.apply { submitList(movieDto) }
    }
}
