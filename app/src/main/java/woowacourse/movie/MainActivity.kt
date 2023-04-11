package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieListView = findViewById<ListView>(R.id.listView)
        val adapter = MovieAdapter(layoutInflater, initMovieData())
        movieListView.adapter = adapter

        adapter.clickListener = object : MovieAdapter.ReservationClickListener {
            override fun onClick(position: Int) {
                inten(adapter.movie[position])
            }
        }
    }

    private fun inten(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE_DATA, movie)
        startActivity(intent)
    }

    private fun initMovieData(): List<Movie> {
        return mutableListOf<Movie>().apply {
            add(
                Movie(
                    R.drawable.slamdunk,
                    "더 퍼스트 슬램덩크",
                    LocalDate.of(2023, 1, 4),
                    124
                )
            )
        }
    }

    companion object {
        val KEY_MOVIE_DATA = "key_movie_data"
    }
}
