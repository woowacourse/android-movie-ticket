package woowacourse.movie

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val runningTime: Int = 0,
    @DrawableRes val poster: Int = 0,
) : Parcelable {
    companion object {
        val movies: List<Movie> =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                    poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
                ),
            )
    }
}
