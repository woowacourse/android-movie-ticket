package woowacourse.movie.feature.movieSelect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.reservation.ReservationActivity
import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData

class MovieSelectActivity :
    AppCompatActivity(),
    MovieSelectContract.View {
    private val present: MovieSelectPresenter = MovieSelectPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rv_main_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        present.initMainUI()
    }

    override fun initMovieListUI(
        screenings: List<Screening>,
        ads: List<Advertisement>,
    ) {
        val movieListView = findViewById<RecyclerView>(R.id.rv_main_movies)

        val movieAdapter = MovieAdapter(screenings, ads, present::navigateToReservationUI)
        movieListView.adapter = movieAdapter
        movieListView.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToReservationUI(screeningData: ScreeningData) {
        startActivity(ReservationActivity.newIntent(this, screeningData))
    }
}
