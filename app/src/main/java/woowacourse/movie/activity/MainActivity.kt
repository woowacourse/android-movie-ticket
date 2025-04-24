package woowacourse.movie.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.global.ServiceLocator

class MainActivity : AppCompatActivity(), MainContract.View {
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

        val presenter = ServiceLocator.mainPresenter
        val movies = presenter.movies()
        val movieListView = binding.movies
        val movieListAdapter = MovieListAdapter(movies, ::navigate)
        movieListView.adapter = movieListAdapter
    }

    override fun navigate(movieDto: MovieDto) {
        val intent = ReservationActivity.newIntent(this, movieDto)
        startActivity(intent)
    }
}
