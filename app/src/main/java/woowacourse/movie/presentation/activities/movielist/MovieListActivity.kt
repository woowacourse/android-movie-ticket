package woowacourse.movie.presentation.activities.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.movielist.adapter.MovieListAdapter
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieListActivity : AppCompatActivity() {
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initMovieListAdapter()
    }

    private fun initMovieListAdapter() {
        movieListAdapter = MovieListAdapter(
            movies = Movie.provideDummy(),
            ads = Ad.provideDummy(),
            onBookBtnClick = { movie -> startTicketingActivity(movie) },
            onAdClick = { ads -> accessAdWebPage(ads) }
        )
        findViewById<RecyclerView>(R.id.movies_rv).adapter = movieListAdapter
    }

    private fun startTicketingActivity(movie: Movie) {
        val intent = Intent(this, TicketingActivity::class.java)
            .putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    private fun accessAdWebPage(ads: Ad) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ads.url))
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie_key"
    }
}
