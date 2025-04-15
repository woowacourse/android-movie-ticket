package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Movie
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieListView = findViewById<ListView>(R.id.lv_main_movies)
        movieListView.adapter =
            MovieAdapter(
                this,
                Movie.values,
                ::navigateToReservationComplete,
            )
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val bundle =
            Bundle().apply {
                putString(ReservationCompleteActivity.MOVIE_TITLE_KEY, movie.title)
                putString(
                    ReservationCompleteActivity.MOVIE_SCREENING_DATE_KEY,
                    movie.screeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                )
            }
        val intent =
            Intent(this, ReservationCompleteActivity::class.java).apply { putExtras(bundle) }
        startActivity(intent)
    }
}
