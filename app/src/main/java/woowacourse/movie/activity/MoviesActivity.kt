package woowacourse.movie.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import domain.Movie
import woowacourse.movie.MockAdvertisementFactory
import woowacourse.movie.MockMoviesFactory
import woowacourse.movie.R
import woowacourse.movie.view.MovieAdapter
import woowacourse.movie.view.model.AdvertisementUiModel

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val movies = MockMoviesFactory.generateMovies()
        val advertisementViewModel = MockAdvertisementFactory.generateAdvertisement()
        val movieList = findViewById<RecyclerView>(R.id.main_movie_list)
        movieList.adapter = MovieAdapter(
            movies,
            advertisementViewModel,
            ::advertisementClick,
            ::reservationButtonClick
        )
    }

    private fun reservationButtonClick(movie: Movie) {
        MovieReservationActivity.start(this, movie)
    }

    private fun advertisementClick(advertisementUiModel: AdvertisementUiModel) {
        val url = advertisementUiModel.url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
