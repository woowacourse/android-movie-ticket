package woowacourse.movie.ui.view.main

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.data.Movie
import woowacourse.movie.ui.adapter.MovieAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        val adapter = MovieAdapter(this, movies)
        val listView = findViewById<ListView>(R.id.movies)
        listView.adapter = adapter
    }

    private fun setupScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private val movies =
            listOf(
                Movie(
                    "승부",
                    LocalDate.of(2025, 3, 26),
                    LocalDate.of(2025, 4, 26),
                    115,
                    R.drawable.match,
                ),
                Movie(
                    "미키 17",
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 29),
                    137,
                    R.drawable.mickey,
                ),
            )
    }
}
