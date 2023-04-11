package woowacourse.movie

import android.graphics.drawable.Drawable
import java.time.LocalDate

data class Movie(
    val imgDrawable: Drawable?,
    val title: String,
    val date: LocalDate,
    val runningTime: Int,
)
