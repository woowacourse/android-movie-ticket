package woowacourse.movie.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.MovieInfo
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.home.adapter.HomeAdapter
import woowacourse.movie.ui.home.adapter.ItemClickListener

class HomeActivity : AppCompatActivity() {
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initHomeAdapter()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(
            object : ItemClickListener {
                override fun onMovieItemClick(movie: MovieInfo.Movie) {
                    setClickEventOnSelectedMovie(movie)
                }

                override fun onAdItemClick(ad: MovieInfo.Advertisement) {
                    setClickEventOnAdvertisement(ad)
                }
            },
            MovieData.getMoviesWithAds(),
        )

        findViewById<RecyclerView>(R.id.rv_main_movie_list).adapter = homeAdapter
    }

    private fun setClickEventOnSelectedMovie(movie: MovieInfo.Movie) {
        val intent = BookingActivity.getIntent(this, movie.id)
        startActivity(intent)
    }

    private fun setClickEventOnAdvertisement(ad: MovieInfo.Advertisement) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
        startActivity(intent)
    }
}
