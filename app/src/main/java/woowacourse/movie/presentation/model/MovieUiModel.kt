package woowacourse.movie.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MovieUiModel(
    val title: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val runningTime: Int = 0,
    @DrawableRes val poster: Int = 0,
) : Parcelable
