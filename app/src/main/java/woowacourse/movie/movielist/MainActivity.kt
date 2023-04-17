package woowacourse.movie.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieDummy
import woowacourse.movie.moviedetail.MovieDetailActivity

class MainActivity : AppCompatActivity(), OnMovieClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieDatas()
    }

    override fun onMovieClick(movie: MovieDto) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, movie)
        startActivity(intent)
    }

    private fun setUpMovieDatas() {
        val movieListView = findViewById<ListView>(R.id.movie_listView)
        val movieListViewAdapter = MovieListViewAdapter(MovieDummy.movieDatas, this)

        movieListView.adapter = movieListViewAdapter
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
