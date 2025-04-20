package woowacourse.movie.ui.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.MovieRepository
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.view.booking.BookingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        val adapter =
            MovieAdapter(
                this,
                MovieRepository.getMovies(),
                onReservationClickListener =
                    { movie ->
                        val intent = Intent(this, BookingActivity::class.java)
                        intent.putExtra(getString(R.string.movie_info_key), movie)
                        startActivity(intent)
                    },
            )
        val listView = findViewById<ListView>(R.id.movies)
        listView.adapter = adapter
    }

    private fun setupScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
