package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.service.MovieService

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieService.findAllMovies()
        val movieOrAdvertises = createMovieOrAdvertises(movies)
        val movieAdapter = MovieListAdapter(this, movieOrAdvertises)
        val movieListView = findViewById<ListView>(R.id.movie_listview)
        movieListView.adapter = movieAdapter
    }

    private fun createMovieOrAdvertises(movies: List<MovieDto>): List<MovieOrAdvertise> {
        val movieOrAdvertises = mutableListOf<MovieOrAdvertise>()
        movies.forEachIndexed { index, movieDto ->
            movieOrAdvertises.add(MovieOrAdvertise(movieDto))
            if ((index + 1) % ADVERTISE_INTERVAL == 0) movieOrAdvertises.add(MovieOrAdvertise(null))
        }
        return movieOrAdvertises
    }

    companion object {
        private const val ADVERTISE_INTERVAL = 3
    }
}
