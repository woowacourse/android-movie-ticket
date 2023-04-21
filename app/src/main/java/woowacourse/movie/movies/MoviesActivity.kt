package woowacourse.movie.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MockMoviesGenerator
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.reservation.ReservationActivity

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        applyMoviesAdapter()
    }

    private fun applyMoviesAdapter() {
        val moviesInfo: List<MovieInfo> = MockMoviesGenerator().generate()
        val moviesRecyclerView: RecyclerView = findViewById(R.id.movies_recycler_view)

        moviesRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        moviesRecyclerView.adapter = MoviesAdapter(moviesInfo, ::onReservationButtonClicked)
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
