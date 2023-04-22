package woowacourse.movie.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.DisplayItem
import woowacourse.movie.model.MockAdvertisementGenerator
import woowacourse.movie.model.MockMoviesGenerator
import woowacourse.movie.reservation.ReservationActivity

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        applyMoviesAdapter()
    }

    private fun applyMoviesAdapter() {
        val moviesRecyclerView: RecyclerView = findViewById(R.id.movies_recycler_view)

        moviesRecyclerView.adapter = MoviesAdapter(
            moviesInfo = MockMoviesGenerator().generate(),
            advertisement = MockAdvertisementGenerator().generate(),
            onItemViewClickListener = object : OnItemViewClickListener {
                override fun onDisplayItemClicked(displayItem: DisplayItem) {
                    when (displayItem) {
                        is DisplayItem.Advertisement -> onAdClicked(displayItem)
                        is DisplayItem.MovieInfo -> onReservationButtonClicked(displayItem)
                    }
                }
            }
        )
    }

    private fun onAdClicked(advertisement: DisplayItem.Advertisement) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(advertisement.url))

        startActivity(intent)
    }

    private fun onReservationButtonClicked(movieInfo: DisplayItem.MovieInfo) {
        val intent = Intent(this, ReservationActivity::class.java)

        intent.putExtra(MOVIE_KEY, movieInfo)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_KEY = "movie_key"
    }
}
