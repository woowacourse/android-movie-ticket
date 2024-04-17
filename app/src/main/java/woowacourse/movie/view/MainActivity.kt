package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.presenter.MainPresenter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter()

        mainPresenter.registerMovie(
            "해리포터와 마법사의 돌",
            listOf(LocalDate.of(2024, 3, 1)),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            R.drawable.harry_image1,
        )

        val movieAdapter = MovieListAdapter(this, mainPresenter)
        val listView = findViewById<ListView>(R.id.movie_list)
        listView.adapter = movieAdapter
    }
}
