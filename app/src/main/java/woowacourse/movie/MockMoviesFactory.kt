package woowacourse.movie

import domain.DateRange
import domain.Movie
import domain.Movies
import java.time.LocalDate

object MockMoviesFactory {
    fun generateMovies(): Movies {
        return Movies(
            List(1000) { (generateMovie(it)) }
        )
    }

    private fun generateMovie(number: Int): Movie {
        return Movie(
            R.drawable.poster_harrypotter.toString(),
            "해리 포터${number}",
            DateRange(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
            ),
            153,
            "《해리 포터》(Harry Potter)는 1997년부터 2016년까지 연재된 영국의 작가 J. K. 롤링의 판타지 소설 시리즈이다. 유일한 친척인 이모네 집 계단 밑 벽장에서 생활하던 11살 소년 해리 포터가 호그와트 마법학교에 다니면서 겪게 되는 판타지 이야기를 그리고 있다.",
        )
    }
}
