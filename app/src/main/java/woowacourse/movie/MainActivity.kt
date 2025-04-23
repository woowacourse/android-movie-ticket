package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingActivity.Companion.KEY_MOVIE_DATA
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.model.Movie
import woowacourse.movie.model.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpUi()

        val intentMovieData = requireMainOrFinish() ?: return

        val movieList = listOf(intentMovieData)
        val movieAdapter =
            MovieAdapter(resources, movieList) { movie ->
                startBookingActivity(movie)
            }
        val listView = findViewById<ListView>(R.id.listview_layout)

        listView.adapter = movieAdapter
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun requireMainOrFinish(): Movie? {
        val intentMovieData =
            IntentCompat.getParcelableExtra(intent, KEY_MOVIE_DATA, Movie::class.java)
        if (intentMovieData == null) {
            Log.e("MainActivity", "intentMovieData가 null입니다. 인텐트에 영화 데이터가 포함되지 않았습니다.")
            Toast.makeText(this, getString(R.string.booking_toast_message), Toast.LENGTH_SHORT)
                .show()
            finish()
        }
        return intentMovieData
    }

    private fun startBookingActivity(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE_DATA, movie)
            }
        startActivity(intent)
    }
}
