package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningDate
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieListView = findViewById<ListView>(R.id.movie_listView)

        val movie = Movie("해리포터", RunningDate(Date(20240301), Date(20240401)), 200, "rkrkrkrkrkrk", R.drawable.img)
        val movieListViewAdapter = MovieListViewAdapter(this, mutableListOf(movie))

        movieListView.adapter = movieListViewAdapter
    }
}
