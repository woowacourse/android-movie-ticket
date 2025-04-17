package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.MovieInfo

class MainActivity : AppCompatActivity() {
    private lateinit var allItems: MutableList<MovieInfo>
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allItems = mutableListOf(
            MovieInfo(
                R.drawable.harry_potter_poster,
                "해리 포터와 마법사의 돌",
                "2025.4.1",
                "2025.4.25",
                "152분"
            )
        )

        val listView = findViewById<ListView>(R.id.movie_list)
        adapter = MovieListAdapter(this, allItems)
        listView.adapter = adapter
    }
}
