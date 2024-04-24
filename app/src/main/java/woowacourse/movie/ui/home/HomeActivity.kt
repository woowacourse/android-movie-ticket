package woowacourse.movie.ui.home

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.repository.DummyMovieList
import woowacourse.movie.repository.DummyScreenList
import woowacourse.movie.ui.home.adapter.MovieListAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homePresenter = HomePresenter()

        val movieAdapter = MovieListAdapter(this, DummyScreenList, DummyMovieList)
        val listView = findViewById<ListView>(R.id.home_movie_listview)
        listView.adapter = movieAdapter
    }
}
