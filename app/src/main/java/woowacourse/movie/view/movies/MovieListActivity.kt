package woowacourse.movie.view.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movies.DUMMY
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movies.adapter.MovieAdapter

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
        val items = DUMMY.getAll()
        val adapter =
            MovieAdapter(
                items = items,
                onClickBooking = {
                    moveToBookingComplete(it)
                },
            )

        listView.adapter = adapter
    }

    private fun moveToBookingComplete(movieIdx: Int) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE, movieIdx)
            }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}
