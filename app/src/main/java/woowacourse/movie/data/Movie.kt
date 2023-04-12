package woowacourse.movie.data

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val title: String,
    val date: LocalDate,
    val runningTime: Int,
    val introduce: String,
    @DrawableRes val thumbnail: Int,
) : Serializable {
    val formattedDate: String = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    companion object {
        fun provideDummy(): List<Movie> = listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                LocalDate.of(2024, 3, 1),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                R.drawable.img_default_movie_thumbnail
            )
        )
    }
}
