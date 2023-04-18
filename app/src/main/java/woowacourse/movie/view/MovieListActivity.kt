package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import com.example.domain.Movie
import woowacourse.movie.repository.MovieMockRepository

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieMockRepository.findAll()

        val movieAdapter = MovieListAdapter(
            movies,
            object : MovieListAdapter.OnReserveListener {
                override fun onClick(movie: Movie) {
                    val intent = Intent(this@MovieListActivity, ReservationActivity::class.java)
                    intent.putExtra(MOVIE_ITEM, movie.toUiModel())
                    startActivity(intent)
                }
            })
        val movieListView = findViewById<ListView>(R.id.movie_listview)
        movieListView.adapter = movieAdapter
    }

    companion object {
        const val MOVIE_ITEM = "MOVIE_ITEM"
    }
}
