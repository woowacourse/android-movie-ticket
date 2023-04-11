package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieListView = findViewById<ListView>(R.id.listView)
        val adapter = MovieAdapter(layoutInflater, initMovieData())
        movieListView.adapter = adapter
    }

    private fun initMovieData(): List<Movie> {
        return mutableListOf<Movie>().apply {
            add(
                Movie(
                    ResourcesCompat.getDrawable(resources, R.drawable.slamdunk, null),
                    "더 퍼스트 슬램덩크",
                    LocalDate.of(2023, 1, 4),
                    124,
                ),
            )
        }
    }
}
