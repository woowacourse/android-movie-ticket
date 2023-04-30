package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.MockMovies
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity

class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initViewId()
        initMovieListAdapter()
        initMovieListView()
    }

    private fun initViewId() {
        movieRecyclerView = findViewById(R.id.rv_movie)
    }

    private fun initMovieListAdapter() {
        val mockMovies = MockMovies.generate()
        val advertisementImage = R.drawable.img_ad
        movieListAdapter = MovieListAdapter(mockMovies, advertisementImage, ::onMovieClickListener, ::onAdvertisementClickListener)
    }

    private fun onMovieClickListener(item: MovieDataState) {
        val intent = Intent(this, MovieBookingActivity::class.java)
            .putExtra(MOVIE_DATA, item)
        startActivity(intent)
    }

    private fun onAdvertisementClickListener() {
        val url = getString(R.string.advertisement_url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun initMovieListView() {
        movieRecyclerView.adapter = movieListAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
    }
}
