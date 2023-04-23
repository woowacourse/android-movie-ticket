package woowacourse.movie.presentation.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.presentation.view.main.data.MovieData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListView()
    }

    private fun setListView() {
        val listView = binding.rvMovieList
        val movies = MovieData.getData()
        val adapter = MovieListAdapter(movies, getDrawable(R.drawable.advertise_wooteco)!!)
        listView.adapter = adapter
    }
}
