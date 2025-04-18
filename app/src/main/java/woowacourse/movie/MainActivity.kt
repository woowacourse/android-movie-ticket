package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val startDate: LocalDate = LocalDate.parse("2025.04.01", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.04.25", formatter)

        val movies =
            listOf(
                Movie(
                    "해리포터와 마법사의 돌",
                    ScreeningDate(startDate, endDate),
                    RunningTime(152),
                    R.drawable.harrypotter,
                ),
            )
        val adapter =
            MoviesAdapter(movies) { movie ->
                val intent = Intent(this, ReserveActivity::class.java)
                intent.putExtra(getString(R.string.key_movie), movie)
                startActivity(intent)
            }
        moviesView.adapter = adapter
    }
}
