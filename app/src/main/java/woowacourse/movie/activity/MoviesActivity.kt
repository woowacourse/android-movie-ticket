package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.activity.movieinformation.ReservationActivity
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.databinding.ActivityMoviesBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.util.Mock

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMoviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        val movieListView: ListView = binding.root
        setContentView(movieListView)

        initMovieListView(movieListView)
    }

    private fun initMovieListView(movieListView: ListView) {
        val movieModels: List<MovieModel> = Mock.getMovieModels()
        movieListView.adapter = MoviesAdapter(movieModels)
        movieListView.setOnItemClickListener { _, _, position, _ ->
            movieListItemClickEvent(movieModels[position], ReservationActivity::class.java)
        }
    }

    private fun movieListItemClickEvent(
        movieModel: MovieModel,
        nextActivity: Class<ReservationActivity>
    ) {
        val intent = Intent(this, nextActivity)
        intent.putExtra(MOVIE_INTENT_KEY, movieModel)
        this.startActivity(intent)
    }
}
