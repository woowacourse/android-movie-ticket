package woowacourse.movie.view.screening

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

    private fun initListView() {
        val screenings: List<Screening> = Screenings().value
        val movieListView = findViewById<ListView>(R.id.lv_screening_movies)
        val movieAdapter = ScreeningAdapter(screenings, ::navigateToReservationActivity)
        movieListView.adapter = movieAdapter
    }

    private fun navigateToReservationActivity(screening: Screening) {
        val intent = ReservationActivity.newIntent(this, screening)
        startActivity(intent)
    }
}
