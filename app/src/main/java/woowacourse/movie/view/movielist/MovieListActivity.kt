package woowacourse.movie.view.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.moviedetail.MovieDetailActivity
import woowacourse.movie.view.viewmodel.DummyData
import woowacourse.movie.view.viewmodel.MovieListData.ADData
import woowacourse.movie.view.viewmodel.MovieUIModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setUpMovieRecyclerView()
    }

    private fun setUpMovieRecyclerView() {
        val movieRecyclerView = findViewById<RecyclerView>(R.id.movie_recyclerView)
        val data = DummyData.getItems()
        val movieAdapter = MovieRecyclerAdapter(
            data,
            object : MovieRecyclerAdapter.OnClickItem {
                override fun onClickMovie(position: Int) {
                    val intent = MovieDetailActivity.newIntent(this@MovieListActivity, data[position] as MovieUIModel)
                    startActivity(intent)
                }

                override fun onClickAD(position: Int) {
                    val ad = data[position] as ADData
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
                    startActivity(intent)
                }
            }
        )

        movieRecyclerView.adapter = movieAdapter
    }

    private fun clickAdvertisement(ad: ADData) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.url))
        startActivity(intent)
    }
}
