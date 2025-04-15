package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val moviesAdapter =
            MoviesAdapter(this, movies) { movie ->
                bookMovie(movie)
            }
        findViewById<ListView>(R.id.lv_movies).adapter = moviesAdapter
    }

    private fun bookMovie(movie: Movie) {
        val intent = Intent(this, BookingCompleteActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("date", movie.date.toString())
        startActivity(intent)
    }
}
