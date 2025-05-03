package woowacourse.movie.feature.movieSelect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.movieSelect.adapter.AdvertisementData
import woowacourse.movie.feature.movieSelect.adapter.MovieAdapter
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.reservation.ReservationActivity

class MovieSelectActivity :
    AppCompatActivity(),
    MovieSelectContract.View {
    private lateinit var presenter: MovieSelectContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rv_main_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 0. presenter에 view(자신) 의존성 주입 하며 초기화
        presenter = MovieSelectPresenter(this@MovieSelectActivity)
        // 1. presenter로 표시할 데이터 요청
        presenter.loadMovieList()
    }

    // 6. view는 presenter가 가공한 데이터를 전달받아 표시(recyclerView 등)
    override fun updateMovieList(
        screeningDataList: List<ScreeningData>,
        adsDataList: List<AdvertisementData>,
    ) {
        val movieListView = findViewById<RecyclerView>(R.id.rv_main_movies)

        val movieAdapter =
            MovieAdapter(screeningDataList, adsDataList) { screeningData ->
                presenter.navigateToReservationView(screeningData)
            }
        movieListView.adapter = movieAdapter
        movieListView.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToReservationView(screeningData: ScreeningData) {
        startActivity(ReservationActivity.newIntent(this, screeningData))
    }
}
