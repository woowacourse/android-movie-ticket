package woowacourse.movie.view.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movie.adapter.MovieAdapter

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListView()
    }

    private fun setListView() {
        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                items = Movie.dummy,
                onClickBooking = { moveToBookingComplete(it) },
            )

        listView.adapter = adapter
    }

    private fun moveToBookingComplete(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(
                    KEY_MOVIE,
                    Movie(
                        movie.title,
                        movie.poster,
                        movie.releaseDate,
                        movie.runningTime,
                    ),
                )
            }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}
