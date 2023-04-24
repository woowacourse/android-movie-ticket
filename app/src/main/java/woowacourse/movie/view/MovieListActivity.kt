package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.service.MovieService

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieService.findAllMovies()
        val movieAdapter = MovieListAdapter(this, movies)
        val movieListView = findViewById<ListView>(R.id.movie_listview)
        movieListView.adapter = movieAdapter
    }
}
