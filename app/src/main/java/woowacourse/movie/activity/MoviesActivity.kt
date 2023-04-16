package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.model.ActivityMovieModel
import woowacourse.movie.model.MockMoviesGenerator

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesAdapter()
    }

    private fun initMoviesAdapter() {
        val movies: List<ActivityMovieModel> = MockMoviesGenerator().generate()
        val moviesListView: ListView = findViewById(R.id.movies_list_view)

        applyListAdapter(movies, moviesListView)
    }

    private fun applyListAdapter(movies: List<ActivityMovieModel>, moviesListView: ListView) {
        moviesListView.adapter = MoviesAdapter(movies, ::getReservationEvent)
    }

    private fun getReservationEvent(movie: ActivityMovieModel) {
        val intent = Intent(this, ReservationActivity::class.java)

        intent.putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_KEY = "movie_key"
    }
}
