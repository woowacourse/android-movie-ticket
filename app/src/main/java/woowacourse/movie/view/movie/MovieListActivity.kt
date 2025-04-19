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
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.view.booking.BookingActivity
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        initView()
        setListView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setListView() {
        val itemList =
            (1..100).map {
                Movie(
                    "해리 포터와 마법사의 돌 $it",
                    R.drawable.harry_potter_one,
                    ScreeningDate(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 25),
                    ),
                    "152분",
                )
            }

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                items = itemList,
                onClickBooking = { idx ->
                    moveToBookingComplete(itemList[idx])
                },
            )

        listView.adapter = adapter
    }

    private fun moveToBookingComplete(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra("movie", movie)
            }
        startActivity(intent)
    }

    companion object {
    }
}
