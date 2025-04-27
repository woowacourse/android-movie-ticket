package woowacourse.movie.domain

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes val image: Int,
    val title: String,
    val date: Date,
    val time: Int,
) : Serializable {
    companion object {
        val dummy: List<Movie> =
            listOf(
                Movie(
                    R.drawable.harry,
                    "해리 포터와 마법사의 돌",
                    Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 30)),
                    152,
                ),
            )
    }
}
