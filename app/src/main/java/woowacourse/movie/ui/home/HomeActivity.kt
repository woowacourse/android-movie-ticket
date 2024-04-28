package woowacourse.movie.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.repository.DummyScreenList
import woowacourse.movie.ui.home.adapter.ScreenRecyclerAdapter
import woowacourse.movie.ui.reservation.ReservationActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        homePresenter = HomePresenter()

        val recyclerView= findViewById<RecyclerView>(R.id.home_movie_rv)
        val screenRecyclerAdapter = ScreenRecyclerAdapter(DummyScreenList, ::startReservationActivity)

        recyclerView.adapter = screenRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun startReservationActivity(screenId: Long) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra("screenId", screenId)

        startActivity(intent)
    }
}
