package woowacourse.movie.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.constant.IntentKeys
import java.time.LocalDate

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

    companion object {
        private val movies =
            listOf(
                Movie(
                    "승부",
                    LocalDate.of(2025, 3, 26),
                    LocalDate.of(2025, 4, 26),
                    115,
                ),
                Movie(
                    "미키 17",
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 29),
                    137,
                ),
            )
    }
}
