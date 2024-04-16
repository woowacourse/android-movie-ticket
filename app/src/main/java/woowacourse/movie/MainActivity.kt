package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieListView = findViewById<ListView>(R.id.list_view)

        val movies = mutableListOf<Movie>()
        movies.add(
            Movie(R.drawable.poster, "해리 포터와 마법사의 돌", "2024.3.1", 152),
        )
        val adapter = ListViewAdapter(movies)

        movieListView.adapter = adapter
    }
}
