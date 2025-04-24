package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.util.ErrorUtils

class MainActivity : AppCompatActivity() {
    private lateinit var allItems: MutableList<MovieInfo>
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allItems =
            mutableListOf(
                MovieInfo(
                    R.drawable.harry_potter_poster,
                    "해리 포터와 마법사의 돌",
                    "2025.4.1",
                    "2025.4.25",
                    152,
                ),
            )

        val listView = findViewById<ListView>(R.id.movie_list)
        adapter = MovieListAdapter(this, allItems, ::changeActivity, ::onError)
        listView.adapter = adapter
    }

    private fun changeActivity(item: MovieInfo) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra("MOVIE_INFO", item)
            }
        startActivity(intent)
    }

    private fun onError() {
        ErrorUtils.printError(this)
        finish()
    }
}
