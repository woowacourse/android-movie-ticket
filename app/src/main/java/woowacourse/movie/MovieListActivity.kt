package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.repository.MovieMockRepository
import woowacourse.movie.view.MovieListAdapter

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = MovieMockRepository.findAll()
        val movieAdapter = MovieListAdapter(this, movies)
        val movieList = findViewById<ListView>(R.id.movie_listview)
        movieList.adapter = movieAdapter
    }
}
