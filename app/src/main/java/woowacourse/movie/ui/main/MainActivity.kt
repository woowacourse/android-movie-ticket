package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.advertisement.AdvertisementPolicy
import woowacourse.movie.advertisement.AdvertisementRepository
import woowacourse.movie.model.main.MainMapper.toUiModel
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.main.adapter.listview.MovieAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        val movieAdapter = MovieAdapter(
            this,
            { clickBook(it) },
            { clickAdvertisement(it) },
        )
        findViewById<ListView>(R.id.listMainMovie).adapter = movieAdapter

        val movies = MovieRepository.getMovies().toUiModel()
        val advertisements = AdvertisementRepository.getAdvertisements().toUiModel()
        val data = AdvertisementPolicy.mergeAdvertisement(movies, advertisements)
        movieAdapter.initMovies(data)
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }

    private fun clickAdvertisement(intent: Intent) {
        startActivity(intent)
    }
}
