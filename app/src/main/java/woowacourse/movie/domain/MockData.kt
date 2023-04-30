package woowacourse.movie.domain

import woowacourse.movie.R
import java.time.LocalDate

object MockData {
    private val movies = List(10000) {
        MovieInfo.MovieUnit(
            Movie(
                1,
                "해리 포터와 마법사의 돌",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                R.drawable.harry_potter_thumbnail,
                R.drawable.harry_potter_poster,
            ),
        )
    }
    private val ad = MovieInfo.Advertisement(
        R.drawable.ad,
        "https://github.com/woowacourse",
    )

    fun getMoviesWithAds(): List<MovieInfo> {
        val items = mutableListOf<MovieInfo>()

        movies.forEachIndexed { index, movieUnit ->
            items.add(movieUnit)

            if (isAdPosition(index)) {
                items.add(ad)
            }
        }

        return items
    }

    private fun isAdPosition(index: Int) = index % 3 == 2
}
