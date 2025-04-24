package woowacourse.movie.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.ui.BaseActivity
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.presentation.booking.BookingActivity
import woowacourse.movie.ui.constant.IntentKeys

class MoviesActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_movies
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(this, movies) { movie ->
            val intent = Intent(this, BookingActivity::class.java).apply {
                putExtra(IntentKeys.MOVIE, movie)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(layoutRes)
        val listView = findViewById<ListView>(R.id.listview_movies)
        listView.adapter = movieAdapter
    }
}
