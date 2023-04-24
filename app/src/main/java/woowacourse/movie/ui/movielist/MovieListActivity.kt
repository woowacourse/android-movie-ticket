package woowacourse.movie.ui.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.woowacourse.movie.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.model.MovieUI
import woowacourse.movie.model.mapper.toMovieUI
import woowacourse.movie.ui.movielist.adapter.MovieListAdapter
import woowacourse.movie.ui.movielist.model.AdvertisementUI
import woowacourse.movie.ui.movielist.model.OnItemClick
import woowacourse.movie.ui.ticketing.TicketingActivity

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initMovieData()
    }

    private fun initMovieData() {
        val movieData = initMovieThumbnail()
        Log.d("sunny", "movieData Size: ${movieData.size}")
        val advertisements = initAdvertisement()
        Log.d("sunny", "advertisements Size: ${advertisements.size}")
        setAdapter(movieData, advertisements)
    }

    private fun initMovieThumbnail(): List<MovieUI> =
        runCatching {
            Movie.provideDummy().map { it.toMovieUI() }
        }
            .getOrDefault(emptyList())

    private fun initAdvertisement(): List<AdvertisementUI> =
        runCatching {
            AdvertisementUI.provideDummy().map { it }
        }
            .getOrDefault(emptyList())

    private fun setAdapter(movies: List<MovieUI>, advertisements: List<AdvertisementUI>) {
        val recyclerMovies: RecyclerView = findViewById(R.id.recycler_movies)
        val movieListAdapter =
            MovieListAdapter(
                movies, advertisements,
                object : OnItemClick {
                    override fun onBookClick(item: MovieUI) {
                        this@MovieListActivity.onBookClick(item)
                    }

                    override fun onAdvertisementClick(item: AdvertisementUI) {
                        this@MovieListActivity.onAdvertisementClick(item)
                    }
                }
            )

        recyclerMovies.adapter = movieListAdapter
        Log.d("sunny", "movieListAdapter Size: ${movieListAdapter.itemCount}")
    }

    private fun onBookClick(item: MovieUI) {
        val intent = Intent(this@MovieListActivity, TicketingActivity::class.java)
        intent.putExtra(MOVIE_KEY, item)
        startActivity(intent)
    }

    private fun onAdvertisementClick(item: AdvertisementUI) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie"
    }
}
