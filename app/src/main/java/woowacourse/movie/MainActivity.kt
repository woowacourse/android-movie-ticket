package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

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
