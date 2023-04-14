package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.MoviesMock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeMovieList()
    }

    private fun makeMovieList() {
        val movies = MoviesMock.create()
        val movieList = findViewById<ListView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(this, movies)
    }
}
