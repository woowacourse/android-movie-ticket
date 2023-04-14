package woowacourse.movie.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.ui.moviedetail.MovieDetailActivity
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = getMovies()
        setMovieList(movies)
    }

    private fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                R.drawable.parasite,
                "기생충",
                LocalDate.of(2023, 9, 12),
                LocalDate.of(2023, 9, 30),
                131,
                "직업도 없이 허름한 반지하에 사는 기택 가족에게 돈을 벌 기회가 찾아온다. 친구의 소개로 부잣집 딸 다혜의 과외 선생님을 맡게 된 기택의 아들, 기우는 기대감에 부푼 채 글로벌 IT기업을 이끄는 박 사장의 저택에 들어간다."
            ),
            Movie(
                R.drawable.about_time,
                "About Time",
                LocalDate.of(2023, 4, 10),
                LocalDate.of(2023, 4, 24),
                123,
                "아버지에게 가문 대대로 시간을 돌리는 능력을 타고났다는 사실을 들은 팀. 우연히 만난 메리에게 반한 팀은 완벽한 사랑을 위해 능력을 마음껏 사용하고, 그럴 때마다 주변 상황들이 점점 어긋나기 시작한다."
            ),
            Movie(
                R.drawable.witch,
                "마녀",
                LocalDate.of(2023, 8, 12),
                LocalDate.of(2023, 8, 29),
                125,
                "10년 전 의문의 사고가 일어난 시설에서 홀로 탈출한 후 모든 기억을 잃은 ‘자윤’. 나이도, 이름도 모르는 자신을 거두고 키워준 노부부의 보살핌으로 씩씩하고 밝은 여고생으로 자라났다. 어려운 집안사정을 돕기 위해 상금이 걸린 오디션 프로그램에 출연한 자윤, 방송이 나간 직후부터 의문의 인물들이 그녀 앞에 나타난다. " +
                    "자윤의 주변을 맴돌며 날카롭게 지켜보는 남자 ‘귀공자’, 그리고 과거 사고가 일어난 시점부터 사라진 아이를 찾던 ‘닥터 백’과 ‘미스터 최’까지 자신은 전혀 기억하지 못하는 그들의 등장으로, 자윤은 혼란에 휩싸이게 되는데...! 그들이 나타난 후 모든 것이 바뀌었다."
            )
        )
    }

    private fun setMovieList(movies: List<Movie>) {
        val moviesView = findViewById<ListView>(R.id.main_movie_list)
        moviesView.adapter = MovieListAdapter(
            movies,
            object : MovieListAdapter.ItemButtonClickListener {
                override fun onClick(position: Int) {
                    moveToDetailActivity(movies[position])
                }
            }
        )
        moviesView.setOnItemClickListener { parent, view, position, id ->
            moveToDetailActivity(movies[position])
        }
    }

    private fun moveToDetailActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
