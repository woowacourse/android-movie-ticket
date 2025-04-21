package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.domain.MovieInfo

class MainActivity : AppCompatActivity() {
    private lateinit var allItems: MutableList<MovieInfo>
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allItems =
            mutableListOf(
                MovieInfo(
                    R.drawable.harry_potter_poster,
                    "해리 포터와 마법사의 돌, 더 위대하고 고져스한 어쩌구저쩌구 3줄 이상넘기면 어떤 일이일어날까요 두구두구두구두두구 아 5줄이었습니다 여기서 글을 더 써보도록 하죠 이정도면 5줄이려나요?",
                    "2025.4.1",
                    "2025.4.25",
                    152,
                ),
            )

        val listView = findViewById<ListView>(R.id.movie_list)
        adapter = MovieListAdapter(this, allItems)
        listView.adapter = adapter
    }
}
