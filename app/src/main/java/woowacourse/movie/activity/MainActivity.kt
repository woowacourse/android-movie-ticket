package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MockMoviesFactory
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.MovieAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movies = MockMoviesFactory.generateMovies()
        val movieList = findViewById<ListView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(movies, ::reservationButtonClick)
    }

    private fun reservationButtonClick(movie: Movie) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MOVIE_KEY_VALUE, movie)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_KEY_VALUE = "movie"
    }
}
