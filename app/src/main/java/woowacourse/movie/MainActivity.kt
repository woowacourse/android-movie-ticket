package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val moviesView = findViewById<ListView>(R.id.lv_movies)
        moviesView.adapter = MovieAdapter(MOVIES)
    }
}
