package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MovieClickListener {
    private lateinit var movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = mutableListOf<Movie>(Movie(R.drawable.titanic, "타이타닉", "설명", 1713251625, 152))
        val movieAdapter = MovieAdapter(this, this, movies)
        findViewById<ListView>(R.id.movieList).adapter = movieAdapter
        movieDetailActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    }

    override fun onClick(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        movieDetailActivityResultLauncher.launch(intent)
    }
}
