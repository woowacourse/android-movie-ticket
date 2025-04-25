package woowacourse.movie.view.main

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.presenter.MainPresenter
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.reservation.ReservationActivity

class MainActivity :
    AppCompatActivity(),
    MainView {
    private val present: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv_main_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        present.initMainUI()
    }

    override fun initMovieListUI(screenings: List<Screening>) {
        val movieListView = findViewById<ListView>(R.id.lv_main_movies)

        val movieAdapter = MovieAdapter(screenings, present::navigateToReservationUI)
        movieListView.adapter = movieAdapter
    }

    override fun navigateToReservationUI(screeningData: ScreeningData) {
        startActivity(ReservationActivity.newIntent(this, screeningData))
    }
}
