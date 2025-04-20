package woowacourse.movie.view.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.view.booking.BookingActivity
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        applyWindowInsets()
        setListView(dummyMovieList())
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setListView(itemList: List<Movie>) {
        val listView = findViewById<ListView>(R.id.list_view)

        with(listView) {
            adapter =
                MovieAdapter(
                    items = itemList,
                    onClickBooking = { idx ->
                        moveToBookingComplete(itemList[idx])
                    },
                )
        }
    }

    private fun dummyMovieList(): List<Movie> =
        listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                R.drawable.harry_potter_one,
                ScreeningDate(
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
                "152분",
            ),
        )

    private fun moveToBookingComplete(movie: Movie) {
        startActivity(BookingActivity.newIntent(this, movie))
    }
}
