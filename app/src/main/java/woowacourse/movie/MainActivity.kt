package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                150,
                R.drawable.poster_harry_potter_and_the_philosophers_stone,
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

    private fun navigateToReservationActivity(
        title: String,
        period: ClosedRange<LocalDate>,
        @DrawableRes posterId: Int,
        runningTime: Int,
    ) {
        val intent =
            Intent(
                this,
                ReservationActivity::class.java,
            ).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_START_YEAR, period.start.year)
                putExtra(EXTRA_START_MONTH, period.start.monthValue)
                putExtra(EXTRA_START_DAY, period.start.dayOfMonth)
                putExtra(EXTRA_END_YEAR, period.endInclusive.year)
                putExtra(EXTRA_END_MONTH, period.endInclusive.monthValue)
                putExtra(EXTRA_END_DAY, period.endInclusive.dayOfMonth)
                putExtra(EXTRA_POSTER_ID, posterId)
                putExtra(EXTRA_RUNNING_TIME, runningTime)
            }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_TITLE = "woowacourse.movie.EXTRA_TITLE"
        const val EXTRA_START_YEAR = "woowacourse.movie.EXTRA_START_YEAR"
        const val EXTRA_START_MONTH = "woowacourse.movie.EXTRA_START_MONTH"
        const val EXTRA_START_DAY = "woowacourse.movie.EXTRA_START_DAY"
        const val EXTRA_END_YEAR = "woowacourse.movie.EXTRA_END_YEAR"
        const val EXTRA_END_MONTH = "woowacourse.movie.EXTRA_END_MONTH"
        const val EXTRA_END_DAY = "woowacourse.movie.EXTRA_END_DAY"
        const val EXTRA_POSTER_ID = "woowacourse.movie.EXTRA_POSTER_ID"
        const val EXTRA_RUNNING_TIME = "woowacourse.movie.EXTRA_RUNNING_TIME"
    }
}
