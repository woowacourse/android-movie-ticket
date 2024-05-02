package woowacourse.movie.presentation.screen.movie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity
import woowacourse.movie.presentation.screen.movie.adapter.MovieAdapter
import woowacourse.movie.presentation.screen.movie.adapter.ScreenView

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieContract.View {
    private val presenter: ScreeningMoviePresenter by lazy {
        ScreeningMoviePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)

        val movieAdapter = MovieAdapter { presenter.registerMovie(it) }

        val recyclerView = findViewById<RecyclerView>(R.id.movie_rlv)
        recyclerView.adapter = movieAdapter
        val item = MovieDao().findAll()
        val ads = listOf(R.drawable.ic_launcher_foreground, R.drawable.screening_advertisement)
        val screenItem: List<ScreenView> = item.chunked(3).flatMap {
            it.map { ScreenView.MovieView(it) } + ScreenView.AdView(ads.shuffled().first())
        }
        movieAdapter.submitList(screenItem)
    }

    override fun startNextActivity(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_ID = "movieId"
    }
}
