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
                Movie("해리포터1", "harry_potter_poster", "2024.3.1", 152, "재밋다"),
            )

        val movieAdapter = MovieListAdapter(this, movieList)
        listView.adapter = movieAdapter
    }
}
