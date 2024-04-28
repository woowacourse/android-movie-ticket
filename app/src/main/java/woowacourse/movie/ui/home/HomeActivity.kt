package woowacourse.movie.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.movie.ScreenView
import woowacourse.movie.repository.DummyScreenList
import woowacourse.movie.repository.ScreenListRepository
import woowacourse.movie.ui.home.adapter.ScreenRecyclerAdapter
import woowacourse.movie.ui.reservation.ReservationActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private lateinit var homePresenter: HomePresenter
    private lateinit var screenRecyclerAdapter: ScreenRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        homePresenter = HomePresenter(this)
        homePresenter.loadScreens(DummyScreenList)
    }

    override fun showScreens(screenListRepository: ScreenListRepository) {
        val recyclerView = findViewById<RecyclerView>(R.id.home_movie_rv)
        screenRecyclerAdapter = ScreenRecyclerAdapter(screenListRepository, homePresenter::onScreenSelected)
        recyclerView.adapter = screenRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun startReservationActivity(screenId: Long) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra("screenId", screenId)
        startActivity(intent)
    }
}
