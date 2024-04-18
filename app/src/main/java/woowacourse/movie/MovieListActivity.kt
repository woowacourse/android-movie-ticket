package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.movie_list_view)

        val movieList =
            arrayListOf(
                Movie(
                    "해리 포터와 마법사의 돌",
                    R.drawable.harry_potter_poster,
                    "2024.3.1",
                    152,
                    getString(R.string.harry_potter_description),
                ),
            )

        val movieAdapter = MovieListAdapter(this, movieList)
        listView.adapter = movieAdapter
    }
}
