package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.presenter.MainPresenter
import woowacourse.movie.repository.MovieData

class MainActivity : AppCompatActivity() {
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter()

        val movieAdapter = MovieListAdapter(this, MovieData.movies)
        val listView = findViewById<ListView>(R.id.movie_list)
        listView.adapter = movieAdapter
    }
}
