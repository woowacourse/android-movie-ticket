package woowacourse.movie.view.screening

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.data.screening.Screenings
import woowacourse.movie.domain.screening.Screening
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

    @Suppress("DEPRECATION")
    private fun Intent.getScreeningsExtra(): Array<Screening>? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(EXTRA_SCREENINGS, Array<Screening>::class.java)

            else -> getSerializableExtra(EXTRA_SCREENINGS) as? Array<Screening>
        }

    private fun initListView() {
        val screenings: List<Screening> =
            intent?.getScreeningsExtra()?.toList() ?: Screenings().value
        val movieListView = findViewById<ListView>(R.id.lv_screening_movies)
        val movieAdapter = ScreeningAdapter(screenings, ::navigateToReservationActivity)
        movieListView.adapter = movieAdapter
    }

    private fun navigateToReservationActivity(screening: Screening) {
        val intent = ReservationActivity.newIntent(this, screening)
        startActivity(intent)
    }

    companion object {
        private const val EXTRA_SCREENINGS = "woowacourse.movie.EXTRA_SCREENINGS"

        fun testIntent(
            context: Context,
            screenings: Array<Screening>,
        ): Intent =
            Intent(context, ScreeningActivity::class.java).putExtra(
                EXTRA_SCREENINGS,
                screenings,
            )
    }
}
