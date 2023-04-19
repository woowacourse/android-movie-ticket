package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieMockData
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView()
    }

    private fun initListView() {
        val listView = findViewById<ListView>(R.id.lv_movie_list)
        val adapter = MovieListAdapter(MovieMockData.movies)
        listView.adapter = adapter
    }
}
