package woowacourse.movie

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes
    val imgResourceId: Int,
    val title: String,
    val date: LocalDate,
    val runningTime: Int,
) : Serializable
