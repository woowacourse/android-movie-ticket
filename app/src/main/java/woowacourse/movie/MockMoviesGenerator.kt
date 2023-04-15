package woowacourse.movie

import domain.movie.Movie
import domain.movie.Name
import domain.movie.ScreeningPeriod
import java.time.LocalDate

class MockMoviesGenerator {

    fun generate() = listOf(
        Movie(
            name = Name("해리포터"),
            posterImage = R.drawable.image_movie_poster_harry_potter,
            screeningPeriod = ScreeningPeriod(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30)),
            runningTime = 120,
            description = "《해리 포터》(Harry Potter)는 1997년부터 2016년까지 연재된 영국의 작가 J. K. 롤링의 판타지 소설 시리즈이다. 유일한 친척인 이모네 집 계단 밑 벽장에서 생활하던 11살 소년 해리 포터가 호그와트 마법학교에 다니면서 겪게 되는 판타지 이야기를 그리고 있다. 주로 해리 포터와 볼드모트의 충돌을 다루고 있다. 1997년 6월 26일 첫 번째 책인 《해리 포터와 마법사의 돌》이 출판되어 전 세계적으로 엄청난 인기를 끌었으며, 상업적인 인기를 끌었다. 해리 포터 시리즈가 큰 성공을 거두면서 전 세계적으로 인기를 얻었으며, 영화를 비롯한 다양한 상품들이 제작되었다. 또한, 해리 포터 시리즈는 역사상 성경 다음으로 가장 많이 팔린 책이다. 또한 마지막 책인 《해리 포터와 죽음의 성물》은 발매 하루 만에 미국에서 약 1,100만 부가 팔렸다. 작가 J. K. 롤링에 따르면 그녀는 맨체스터에서 런던으로 가는 기차 안에서 처음 이 이야기를 떠올려, 에딘버러에 있는 작은 카페에서 어린 딸을 어르며 연작의 첫 권을 썼다고 한다."
        )
    )
}
