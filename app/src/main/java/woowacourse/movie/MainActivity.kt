package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime

class MainActivity : AppCompatActivity() {
    private lateinit var moviesView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        moviesView = findViewById(R.id.lv_movies)
        val movies =
            listOf(
                Movie(
                    "해리포터와 마법사의 돌",
                    "2025.4.1",
                    RunningTime(152),
                    R.drawable.harrypotter,
                ),
            )
        val adapter = MoviesAdapter(movies)
        moviesView.adapter = adapter
    }
}
