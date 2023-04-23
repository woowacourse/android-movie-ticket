package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.advertisement.AdvertisementPolicy
import woowacourse.movie.advertisement.AdvertisementRepository
import woowacourse.movie.model.main.MainMapper.toUiAdvertisements
import woowacourse.movie.model.main.MainMapper.toUiMovies
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.main.adapter.recyclerview.MainAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        /*val movieAdapter = MovieAdapter(
            this,
            { clickBook(it) },
            { clickAdvertisement(it) },
        )*/
        val mainAdapter = MainAdapter(
            this,
            { clickBook(it) },
            { clickAdvertisement(it) },
        )
        findViewById<RecyclerView>(R.id.listMainMovie).adapter = mainAdapter

        val movies = MovieRepository.getMovies().toUiMovies()
        val advertisements = AdvertisementRepository.getAdvertisements().toUiAdvertisements()
        val data = AdvertisementPolicy.mergeAdvertisement(movies, advertisements)
        mainAdapter.initMovies(data)
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }

    private fun clickAdvertisement(intent: Intent) {
        startActivity(intent)
    }
}
