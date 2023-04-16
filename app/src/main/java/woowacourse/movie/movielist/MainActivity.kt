package woowacourse.movie.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.mapper.mapToMovieDto
import woowacourse.movie.moviedetail.MovieDetailActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity(), OnMovieClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieDatas()
    }

    private fun setMovieData(): List<MovieDto> = listOf(
        mapToMovieDto(
            Movie(
                "해리포터",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 4, 1),
                200,
                getString(R.string.description),
                R.drawable.img,
            ),
        ),
    )

    override fun onMovieClick(movie: MovieDto) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    private fun setUpMovieDatas() {
        val movieListView = findViewById<ListView>(R.id.movie_listView)
        val movieListViewAdapter = MovieListViewAdapter(this, setMovieData(), this)

        movieListView.adapter = movieListViewAdapter
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
