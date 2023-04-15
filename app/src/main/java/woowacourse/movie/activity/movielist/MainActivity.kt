package woowacourse.movie.activity.movielist

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.dummyData.MovieData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieDatas()
    }

    private fun setMovieData(): List<Movie> = MovieData.dummyData

    private fun setUpMovieDatas() {
        val movieListView = findViewById<ListView>(R.id.movie_listView)
        val movieListViewAdapter = MovieListViewAdapter(this, setMovieData())

        movieListView.adapter = movieListViewAdapter
    }
}
