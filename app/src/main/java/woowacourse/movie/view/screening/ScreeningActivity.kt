package woowacourse.movie.view.screening

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.data.screening.Screenings
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.view.reservation.ReservationActivity
import woowacourse.movie.view.screening.adapter.ScreeningAdapter

class ScreeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screening)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv_screening_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListView()
    }

    private fun initListView() {
        val screenings: List<Screening> = Screenings().value
        val movieListView = findViewById<ListView>(R.id.lv_screening_movies)
        val movieAdapter = ScreeningAdapter(screenings, ::navigateToReservationActivity)
        movieListView.adapter = movieAdapter
    }

    private fun navigateToReservationActivity(screening: Screening) {
        val intent =
            Intent(
                this,
                ReservationActivity::class.java,
            ).apply {
                putExtra(EXTRA_SCREENING, screening)
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
        const val EXTRA_SCREENING = "woowacourse.movie.EXTRA_SCREENING"
    }
}
