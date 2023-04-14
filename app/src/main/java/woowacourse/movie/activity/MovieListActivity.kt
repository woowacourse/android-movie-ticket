package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.model.Movie
import woowacourse.movie.model.PlayingTimes
import woowacourse.movie.util.DummyData
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter = MovieListAdapter(DummyData.movies)
        listView.adapter = adapter
    }
}
