package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.databinding.ActivityMoviesBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.util.Mock

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMoviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        val moviesListView: ListView = binding.root
        setContentView(moviesListView)

        initMoviesView(moviesListView)
    }

    private fun initMoviesView(moviesListView: ListView) {
        val movieModels: List<MovieModel> = Mock.getMovieModels()
        moviesListView.adapter = MoviesAdapter(movieModels)
        moviesListView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra(MOVIE_INTENT_KEY, movieModels[position])
            this.startActivity(intent)
        }
    }
}
