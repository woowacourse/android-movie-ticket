package woowacourse.movie.presentation.movieList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.movieList.adapter.MovieAdapter
import woowacourse.movie.presentation.ticketing.TicketingActivity

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        presenter.loadMovies()
    }

    override fun displayMovies(movies: List<Movie>) {
        val movieList: RecyclerView = findViewById(R.id.rv_movies)
        movieList.adapter = MovieAdapter(movies, presenter)
    }

    override fun navigate(movieId: Int) {
        startActivity(TicketingActivity.createIntent(this, movieId))
    }
}
