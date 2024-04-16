package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.movie_list_view)

        var movieList =
            arrayListOf(
                "해리포터",
            )

        val movieAdapter = MovieListAdapter(this, movieList)
        listView.adapter = movieAdapter
    }
}
