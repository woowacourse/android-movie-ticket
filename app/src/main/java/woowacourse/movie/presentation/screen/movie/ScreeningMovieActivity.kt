package woowacourse.movie.presentation.screen.movie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity
import woowacourse.movie.presentation.screen.movie.adapter.MovieAdapter

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
        // 어뎁터한테 item 넣어주기
        movieAdapter.submitList(item)
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
