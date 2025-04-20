package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Screening
import woowacourse.movie.view.model.ScreeningData
import java.time.LocalDate

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
        val harryPotter =
            Movie(
                "해리 포터와 마법사의 돌",
                152,
                HARRY_POTTER_1_MOVIE_ID,
            )
        val harryPotterScreening =
            Screening(
                harryPotter,
                LocalDate.of(2025, 4, 1)..LocalDate.of(2025, 4, 25),
            )
        val screenings: List<Screening> = listOf(harryPotterScreening)

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

        val intent =
            Intent(this, ReservationActivity::class.java).apply {
                putExtra(EXTRA_SCREENING_DATA, screeningData)
            }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_SCREENING_DATA = "woowacourse.movie.EXTRA_SCREENING_DATA"

        const val HARRY_POTTER_1_MOVIE_ID = "HarryPotter1"
    }
}
