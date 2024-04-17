package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListView()
    }

    private fun setupListView() {
        val movies = createMovieList()
        val movieListView = findViewById<ListView>(R.id.list_view)
        movieListView.adapter =
            ListViewAdapter(movies) { position ->
                startReservationActivity(movies[position])
            }
    }

    private fun createMovieList(): MutableList<Movie> {
        return mutableListOf<Movie>().apply {
            add(
                Movie(
                    R.drawable.poster,
                    "해리 포터와 마법사의 돌",
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                        "영국과 미국 합작, 판타지 영화입니다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품으로, 크리스 콜럼버스가 감독을 맡았습니다.",
                    "2024.3.1",
                    152,
                ),
            )
        }
    }

    private fun startReservationActivity(movie: Movie) {
        Intent(this, ReservationActivity::class.java).apply {
            putExtra("movie", movie)
            startActivity(this)
        }
    }
}
