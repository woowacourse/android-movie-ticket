package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListView()
    }

    private fun setListView() {
        val listView = findViewById<ListView>(R.id.lv_movie_list)
        val movies = listOf(Movie(R.drawable.harry_poter, "해리포터", "2023.04.11", "123분"))
        val adapter = MovieListAdapter(movies)
        listView.adapter = adapter
    }
}
