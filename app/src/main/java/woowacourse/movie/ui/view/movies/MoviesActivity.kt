package woowacourse.movie.ui.view.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.booking.BookingActivity

class MoviesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_main)
        val adapter =
            MovieAdapter(
                this,
                MovieRepository.movies,
                onReservationClickListener =
                    { movieId ->
                        val intent = Intent(this, BookingActivity::class.java)
                        intent.putExtra(getString(R.string.movie_info_key), movieId)
                        startActivity(intent)
                    },
            )
        val listView = findViewById<ListView>(R.id.movies)
        listView.adapter = adapter
    }
}
