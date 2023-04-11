package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieListView = findViewById<ListView>(R.id.movie_listView)

        val movie = Movie("해리포터", 200, "dfdfdfd", R.drawable.movie_harry)
        val ticket = Ticket(13000, Date(20230202), movie)
        val movieListViewAdapter = MovieListViewAdapter(mutableListOf(ticket))

        movieListView.adapter = movieListViewAdapter
    }
}
