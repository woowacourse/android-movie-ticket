package woowacourse.movie.view.main

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.reservation.ReservationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv_main_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListView()
    }

    private fun initListView() {
        val screenings = Screening.getDefaultScreenings()

        val movieListView = findViewById<ListView>(R.id.lv_main_movies)

        val movieAdapter = MovieAdapter(screenings, ::navigateToReservationActivity)
        movieListView.adapter = movieAdapter
    }

    private fun navigateToReservationActivity(screening: Screening) {
        val screeningData =
            ScreeningData(
                title = screening.title,
                startDate = screening.period.start,
                endDate = screening.period.endInclusive,
                movieId = screening.movieId,
                runningTime = screening.runningTime,
            )

        startActivity(ReservationActivity.newIntent(this, screeningData))
    }
}
