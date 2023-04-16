package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.mock.Mock
import woowacourse.movie.uimodel.MovieModel

class MoviesActivity : AppCompatActivity() {

    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesView()
    }

    private fun initMoviesView() {
        val movieModels: List<MovieModel> = Mock.getMovieModels()
        moviesListView.adapter = MoviesAdapter(movieModels)
        moviesListView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra(this.getString(R.string.movie_key), movieModels[position])
            this.startActivity(intent)
        }
    }
}
