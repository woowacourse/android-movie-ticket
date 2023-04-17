package woowacourse.movie

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.entity.RunningTime
import java.time.LocalDate

@Parcelize
class Movie constructor(
    @DrawableRes
    val imgResourceId: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: RunningTime,
    val description: String,
) : Parcelable
