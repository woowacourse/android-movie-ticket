package woowacourse.movie.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MockAdvertisementGenerator
import woowacourse.movie.model.MockMovieGenerator
import woowacourse.movie.model.MovieRecyclerItem
import woowacourse.movie.model.MovieRecyclerItemViewType
import woowacourse.movie.reservation.ReservationActivity

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        applyMoviesAdapter()
    }

    private fun getMovieRecyclerItems(movieRecyclerItemsSize: Int = 100): List<MovieRecyclerItem> {
        val adGenerator = MockAdvertisementGenerator()
        val movieGenerator = MockMovieGenerator()

        return List(movieRecyclerItemsSize) { position ->
            when (MovieRecyclerItemViewType.valueOf(position)) {
                MovieRecyclerItemViewType.MOVIE -> movieGenerator.generate()
                MovieRecyclerItemViewType.ADVERTISEMENT -> adGenerator.generate()
            }
        }
    }

    private fun applyMoviesAdapter() {
        val moviesRecyclerView: RecyclerView = findViewById(R.id.movies_recycler_view)

        moviesRecyclerView.adapter = MoviesAdapter(
            moviesRecyclerItems = getMovieRecyclerItems()
        ) { movieRecyclerItem ->
            when (movieRecyclerItem) {
                is MovieRecyclerItem.Advertisement -> onAdClicked(movieRecyclerItem)
                is MovieRecyclerItem.MovieInfo -> onReservationButtonClicked(movieRecyclerItem)
            }
        }
    }

    private fun onAdClicked(advertisement: MovieRecyclerItem.Advertisement) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(advertisement.url))

        startActivity(intent)
    }

    private fun onReservationButtonClicked(movieInfo: MovieRecyclerItem.MovieInfo) {
        val intent = Intent(this, ReservationActivity::class.java)

        intent.putExtra(MOVIE_KEY, movieInfo)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_KEY = "movie_key"
    }
}
