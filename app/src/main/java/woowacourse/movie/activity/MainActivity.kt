package woowacourse.movie.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.BundleKeys
import woowacourse.movie.R
import woowacourse.movie.movie.MovieMockData
import woowacourse.movie.movielist.MovieRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()
    }

    private fun setMovieRecyclerView() {
        val movieRecyclerView = findViewById<RecyclerView>(R.id.rv_movie_list)

        val movieRecyclerViewAdapter = MovieRecyclerViewAdapter(
            MovieMockData.movies10000,
            Ad.dummyAd,
            getMovieOnClickListener(),
            getAdOnClickListener(),
        )
        movieRecyclerView.adapter = movieRecyclerViewAdapter
        movieRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun getAdOnClickListener() = { item: Ad ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        this.startActivity(intent)
    }

    private fun getMovieOnClickListener() = { position: Int ->
        val intent = MovieDetailActivity.intent(this)
        intent.putExtra(BundleKeys.MOVIE_DATA_KEY, MovieMockData.movies10000[position])
        this.startActivity(intent)
    }
}
