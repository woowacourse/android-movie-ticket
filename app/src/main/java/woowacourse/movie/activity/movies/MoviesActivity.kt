package woowacourse.movie.activity.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.reservation.ReservationActivity
import woowacourse.movie.adapter.MoviesAdapter
import woowacourse.movie.model.MockMoviesGenerator
import woowacourse.movie.model.MovieInfo

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesAdapter()
    }

    private fun initMoviesAdapter() {
        val movies: List<MovieInfo> = MockMoviesGenerator().generate()
        val moviesListView: ListView = findViewById(R.id.movies_list_view)

        applyListAdapter(movies, moviesListView)
    }

    private fun applyListAdapter(movies: List<MovieInfo>, moviesListView: ListView) {
        moviesListView.adapter = MoviesAdapter(movies, ::onReservationButtonClicked)
    }

    private fun onReservationButtonClicked(movie: MovieInfo) {
        val intent = Intent(this, ReservationActivity::class.java)

        intent.putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_KEY = "movie_key"
    }
}
