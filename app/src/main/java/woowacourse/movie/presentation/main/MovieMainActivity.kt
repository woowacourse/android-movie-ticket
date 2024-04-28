package woowacourse.movie.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.presentation.adapter.MovieAdapter
import woowacourse.movie.presentation.detail.MovieDetailActivity
import woowacourse.movie.utils.MovieErrorCode
import woowacourse.movie.utils.MovieIntentConstants.EXTRA_MOVIE_ID

class MovieMainActivity : AppCompatActivity(), MovieMainContract.View {
    private lateinit var movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieMainPresenter: MovieMainPresenter
    private lateinit var movieAdapter: MovieAdapter
    private val movieList: RecyclerView by lazy { findViewById(R.id.mainList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        movieDetailActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.code) {
                    Toast.makeText(this, MovieErrorCode.INVALID_MOVIE_ID.msg, Toast.LENGTH_SHORT).show()
                }
            }

        movieMainPresenter = MovieMainPresenter(this)
        movieMainPresenter.loadMovies()
    }

    override fun onMovieItemClick(id: Long) {
        Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, id)
            movieDetailActivityResultLauncher.launch(this)
        }
    }

    override fun onInitView(movies: List<Movie>) {
        movieAdapter = MovieAdapter(::onMovieItemClick, movies)
        movieList.adapter = movieAdapter
    }
}
