package woowacourse.movie.presentation.activities.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ActivityMovieListBinding
import woowacourse.movie.presentation.activities.movielist.adapter.MovieListAdapter
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMovieListAdapter()
    }

    private fun setMovieListAdapter() {
        movieListAdapter = MovieListAdapter(
            movies = Movie.provideDummy(),
            ads = Ad.provideDummy(),
            onBookBtnClick = { movie -> startTicketingActivity(movie) },
            onAdClick = { ads -> accessAdSite(ads) }
        )
        binding.lvMovies.adapter = movieListAdapter
    }

    private fun startTicketingActivity(movie: Movie) {
        val intent = Intent(this, TicketingActivity::class.java)
            .putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    private fun accessAdSite(ads: Ad) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ads.url))
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie_key"
    }
}
