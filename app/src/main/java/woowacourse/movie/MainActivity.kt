package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
            Array(1) {
                Movie(
                    "해리 포터와 마법사의 돌",
                    R.drawable.harry_potter_one,
                    "2025.4.1",
                    "152분",
                )
            }

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                items = itemList.toList(),
                onClickBooking = { idx ->
                    moveToBookingComplete(itemList[idx])
                },
            )

        listView.adapter = adapter
    }

    private fun moveToBookingComplete(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE_TITLE, movie.title)
                putExtra(KEY_MOVIE_POSTER, movie.poster)
                putExtra(KEY_MOVIE_RELEASE_DATE, movie.releaseDate)
                putExtra(KEY_MOVIE_RUNNING_TIME, movie.runningTime)
            }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE_TITLE = "MOVIE_TITLE"
        const val KEY_MOVIE_POSTER = "MOVIE_POSTER"
        const val KEY_MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE"
        const val KEY_MOVIE_RUNNING_TIME = "MOVIE_RUNNING_TIME"
    }
}
