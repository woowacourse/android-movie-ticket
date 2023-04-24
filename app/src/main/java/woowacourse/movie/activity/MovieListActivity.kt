package woowacourse.movie.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.model.Movie
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.mapper.toMovieModel
import woowacourse.movie.model.MovieListItem
import java.time.LocalDate

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MovieListAdapter(
            getMovieListData()
        ) { item ->
            when (item) {
                is MovieListItem.MovieModel -> {
                    val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                    intent.putExtra(MOVIE_KEY, item)
                    this@MovieListActivity.startActivity(intent)
                }
                is MovieListItem.AdModel -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                    this@MovieListActivity.startActivity(intent)
                }
            }
        }
        recyclerView.adapter = adapter
    }

    private fun getMovieListData(): List<MovieListItem> {
        val movies = getMovieData()
        val items = mutableListOf<MovieListItem>()

        movies.foldIndexed(0) { movieIndex, adIndex, movie ->
            items.add(movie)
            addAdItemByCondition(items, movieIndex, adIndex)
        }

        return items
    }

    private fun addAdItemByCondition(
        items: MutableList<MovieListItem>,
        movieIndex: Int,
        adIndex: Int
    ): Int {
        val ads = getAdData()
        if ((movieIndex + 1) % AD_CYCLE == 0) {
            items.add(ads[adIndex % ads.size])
            return adIndex + 1
        }
        return adIndex
    }

    private fun getMovieData() = List(1000) {
        Movie(
            R.drawable.movie_poster,
            "해리포터 $it",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
        ).toMovieModel()
    }

    private fun getAdData() = List(500) {
        MovieListItem.AdModel(R.drawable.advertisement, "https://techcourse.woowahan.com/")
    }

    companion object {
        private const val AD_CYCLE = 3
        private const val MOVIE_KEY = "movie"
    }
}
