package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import domain.movie.Movie
import woowacourse.movie.MockMoviesGenerator
import woowacourse.movie.R
import woowacourse.movie.adapter.MoviesAdapter

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesAdapter()
    }

    private fun initMoviesAdapter() {
        val movies: List<Movie> = MockMoviesGenerator().generate()
        val moviesListView: ListView = findViewById(R.id.movies_list_view)

        applyListAdapter(movies, moviesListView)
    }

    private fun applyListAdapter(movies: List<Movie>, moviesListView: ListView) {
        moviesListView.adapter = MoviesAdapter(movies, ::getReservationEvent)
    }

    private fun getReservationEvent(movie: Movie) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(getString(R.string.movie_key), movie)
        startActivity(intent)
    }
}
