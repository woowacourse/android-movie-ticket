package woowacourse.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val moviesView = findViewById<ListView>(R.id.lv_movies)
        val movies =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    thumbnail = "https://github.com/kmkim2689/codetree-TILs/assets/101035437/c57f8b0c-5b15-418c-88db-15f2ada4cdcf",
                    date = "2024.3.1",
                    runningTime = 152,
                    introduction = "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
            )

        moviesView.adapter = MovieAdapter(movies)
    }
}

class MovieAdapter(private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, null)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val date = view.findViewById<TextView>(R.id.tv_date)
        val thumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)
        val runningTime = view.findViewById<TextView>(R.id.tv_running_time)

        val movie = movies[position]
        title.text = movie.title
        date.text = "${date.text} ${movie.date}"
        runningTime.text = "${runningTime.text} ${movie.runningTime}"

        return view
    }
}
