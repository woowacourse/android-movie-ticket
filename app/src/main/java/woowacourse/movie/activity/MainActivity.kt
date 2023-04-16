package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieMock
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.Movies
import woowacourse.movie.view.mapper.MovieMapper.toView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieList()
    }

    private fun makeMovieList() {
        val movies = Movies(listOf(MovieMock.create().toView()))
        val movieList = findViewById<ListView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(movies)
        movieList.setOnItemClickListener { parent, _, position, _ ->
            reserveMovie(parent.getItemAtPosition(position) as MovieView)
        }
    }

    private fun reserveMovie(movie: MovieView) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MovieView.MOVIE_EXTRA_NAME, movie)
        startActivity(intent)
    }
}
