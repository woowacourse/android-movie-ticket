package woowacourse.movie.activity.movielist

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.movie.toPresentation
import woowacourse.movie.util.DummyData

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter = MovieListAdapter(DummyData.movies.map { it.toPresentation(R.drawable.img) })
        listView.adapter = adapter
    }
}
