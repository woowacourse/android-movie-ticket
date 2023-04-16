package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.mock.Mock

class MoviesActivity : AppCompatActivity() {

    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesView()
    }

    private fun initMoviesView() {
        val movies: List<Movie> = Mock.getMovies()
        moviesListView.adapter = MoviesAdapter(movies)
        moviesListView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra(this.getString(R.string.movie_key), movies[position])
            this.startActivity(intent)
        }
    }
}
