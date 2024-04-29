package woowacourse.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var movieList: RecyclerView
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieList = findViewById(R.id.rv_movie)

        val dummy = (0..50).map {
            Movie(
                poster = R.drawable.poster_crime,
                title = "범죄도시 $it",
                date = "2024-04-24",
                runTime = "109분"
            )
        }

        adapter = MovieAdapter(::changeMovies)
        movieList.layoutManager = LinearLayoutManager(this)
        movieList.adapter = adapter
        adapter.submitList(dummy)
    }

    private fun changeMovies(movie: Movie) {
        Log.d("james", "title : ${movie.title}")

        val dummy2 = (0..5).map {
            Movie(
                poster = R.drawable.poster_harry,
                title = "해리포터 $it",
                date = "2024-01-15",
                runTime = "129분"
            )
        }
        adapter.submitList(dummy2)
    }
}
