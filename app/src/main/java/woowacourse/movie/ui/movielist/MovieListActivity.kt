package woowacourse.movie.ui.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieItem
import woowacourse.movie.ui.movielist.adapter.MovieListAdapter
import woowacourse.movie.ui.movielist.data.AdRepository
import woowacourse.movie.ui.movielist.data.MovieRepository
import woowacourse.movie.ui.movielist.model.OnItemClick
import woowacourse.movie.ui.ticketing.TicketingActivity

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initAdapter()
    }

    private fun initAdapter() {
        val recyclerMovies: RecyclerView = findViewById(R.id.recycler_movies)
        val movieListAdapter =
            MovieListAdapter(
                MovieRepository.allMovies(),
                AdRepository.allAds(),
                object : OnItemClick {
                    override fun onBookClick(item: MovieItem.MovieUI) {
                        moveToTicketing(item)
                    }

                    override fun onAdvertisementClick(item: MovieItem.AdvertisementUI) {
                        moveToAdvertisement(item)
                    }
                }
            )

        recyclerMovies.adapter = movieListAdapter
    }

    private fun moveToTicketing(item: MovieItem.MovieUI) {
        val intent = TicketingActivity.getIntent(this, item)
        startActivity(intent)
    }

    private fun moveToAdvertisement(item: MovieItem.AdvertisementUI) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie"
    }
}
