package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.MovieState
import java.time.LocalDate

object MovieRepository {
    fun allMovies(): List<MovieState> = movies.toList()

    private val movies: List<MovieState> = List(25) {
        MovieState(
            R.drawable.slamdunk,
            "더 퍼스트 슬램덩크 $it",
            LocalDate.of(2023, 1, 4),
            LocalDate.of(2023, 2, 23),
            124,
            "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
                "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
                "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다."
        )
    }
}
