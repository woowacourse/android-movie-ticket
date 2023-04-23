package woowacourse.movie.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.R
import woowacourse.movie.data.AllRepositories
import woowacourse.movie.data.MovieAndAd
import woowacourse.movie.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieRecyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter: MovieAdapter by lazy { MovieAdapter(initMovieData()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMovieRecyclerView()
    }

    private fun initMovieRecyclerView() {
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieRecyclerView.setHasFixedSize(true)
        movieRecyclerView.adapter = adapter
        adapter.clickListener = object : MovieAdapter.ReservationClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                intent.putExtra(KEY_MOVIE, adapter.allData[position])
                startActivity(intent)
            }
        }
    }

    private fun initMovieData(): List<MovieAndAd> {
        return AllRepositories().restoreRepositories()
    }
}
