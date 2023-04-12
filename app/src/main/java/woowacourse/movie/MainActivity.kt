package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie = Movie(
            R.drawable.parasite,
            "기생충",
            Date(2023, 9, 12),
            131,
            "직업도 없이 허름한 반지하에 사는 기택 가족에게 돈을 벌 기회가 찾아온다. 친구의 소개로 부잣집 딸 다혜의 과외 선생님을 맡게 된 기택의 아들, 기우는 기대감에 부푼 채 글로벌 IT기업을 이끄는 박 사장의 저택에 들어간다."
        )
        val movies = listOf(movie)

        val moviesView = findViewById<ListView>(R.id.main_movie_list)
        moviesView.adapter = MovieListAdapter(
            this, movies,
            object : MovieListAdapter.ItemClickListener {
                override fun onItemClick(position: Int) {
                    moveToDetailActivity()
                }
            }
        )
        moviesView.setOnItemClickListener { parent, view, position, id ->
            moveToDetailActivity()
        }
    }

    private fun moveToDetailActivity() {
        val intent = Intent(this, MovieDetailActivity::class.java)
        startActivity(intent)
    }
}
