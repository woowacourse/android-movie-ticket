package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Date
import woowacourse.movie.model.MovieContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieContentList = findViewById<ListView>(R.id.movie_content_list)
        movieContentList.adapter =
            MovieContentListAdapter(
                listOf(
                    MovieContent(
                        R.drawable.movie_poster,
                        "해리 포터와 마법사의 돌",
                        Date(2024, 3, 1),
                        152,
                    ),
                ),
                this,
            )
    }
}
