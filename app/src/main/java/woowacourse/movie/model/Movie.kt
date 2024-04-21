package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @DrawableRes val poster: Int,
    val title: String,
    val date: String, // TODO: LocalDateTime으로 리팩토링
    val runTime: String
) : Parcelable {
    val information: String = title + "\n" + date + "\n" + runTime
}
