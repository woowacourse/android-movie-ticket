package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val movieAdapter by lazy { MovieAdapter(this) { clickBook(it) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.listMovie).adapter = movieAdapter
        movieAdapter.initMovies(MovieData.movies)
    }

    private fun clickBook(movieId: Long) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("ID", movieId)
        startActivity(intent)
    }
}
