package woowacourse.movie.data

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
    @DrawableRes val thumbnail: Int,
) : Serializable {
    val LocalDate.formattedDate: String
        get() = this.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN))

    companion object {
        private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"

        fun provideDummy(): List<Movie> = listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                R.drawable.img_default_movie_thumbnail1
            ),
            Movie(
                "범죄도시 1",
                LocalDate.of(2023, 4, 4),
                LocalDate.of(2023, 4, 22),
                130,
                "《마동석》은 2001년 써니에 의해 얼굴을 타격받는다.",
                R.drawable.img_default_movie_thumbnail2
            )
        )
    }
}
