package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MyAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movies: List<Movie> = initMovie()
        val listView: ListView = findViewById(R.id.list_view)
        val myAdapter: MyAdapter = MyAdapter(movies)

        listView.adapter = myAdapter
    }

    private fun initMovie(): List<Movie> {
        return listOf(
            Movie(
                R.drawable.harry,
                "해리 포터와 마법사의 돌",
                Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                "152",
            ),
        )
    }
}
