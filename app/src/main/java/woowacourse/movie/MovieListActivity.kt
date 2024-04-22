package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.presenter.MovieListPresenter
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val movies: ListView = findViewById(R.id.lv_movies)
        movies.adapter = presenter.getAdapter()
    }

    override fun navigateToTicketing(movieId: Long) {
        Intent(this, TicketingActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_ID, movieId)
            startActivity(this)
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }
}
