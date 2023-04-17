package woowacourse.movie.activity.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.dummyData.MovieData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieDatas()
    }

    private fun setMovieDataWithDummyData(): List<Movie> = MovieData.dummyData

    private fun setUpMovieDatas() {
        val movieListView = findViewById<ListView>(R.id.movie_listView)
        val movieListViewAdapter = MovieListViewAdapter(this, setMovieDataWithDummyData())

        movieListView.adapter = movieListViewAdapter

        movieListView.setOnItemClickListener { adapterView, _, position, _ ->
            val item = adapterView.getItemAtPosition(position) as Movie
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, item)
            this.startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
