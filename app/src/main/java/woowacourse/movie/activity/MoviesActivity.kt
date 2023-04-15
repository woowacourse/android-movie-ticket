package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MockMoviesGenerator
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter

class MoviesActivity : AppCompatActivity() {

    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesView()
    }

    private fun initMoviesView() {
        moviesListView.adapter = MoviesAdapter(this, MockMoviesGenerator().generate())
    }
}
