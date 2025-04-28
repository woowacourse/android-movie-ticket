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
import woowacourse.movie.dto.MovieListDataDto
import woowacourse.movie.global.ServiceLocator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var movieDto: List<MovieListDataDto>
    private lateinit var presenter: MainContract.Presenter
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initMovieDto(movies: List<MovieListDataDto>) {
        movieDto = movies
        binding.movies.layoutManager = LinearLayoutManager(this)
        binding.movies.adapter =
            MovieListAdapter { movieDto ->
                val intent = ReservationActivity.newIntent(this, movieDto)
                startActivity(intent)
            }.apply { submitList(movieDto) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = ServiceLocator.mainPresenter(this)
        presenter.initMovieDto()
    }
}
