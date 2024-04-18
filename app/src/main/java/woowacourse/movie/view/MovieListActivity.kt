package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.domain.model.Movie

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.movie_list_view)

        val movieList =
            arrayListOf(
                Movie(
                    getString(R.string.harry_potter_title),
                    R.drawable.harry_potter_poster,
                    getString(R.string.harry_potter_screening_date),
                    152,
                    getString(R.string.harry_potter_description),
                ),
            )

        val movieAdapter = MovieListAdapter(this, movieList)
        listView.adapter = movieAdapter
    }
}
