package woowacourse.movie.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDateUiModel(
    val year: Int = 2025,
    val month: Int = 1,
    val day: Int = 1,
) : Parcelable {
    override fun toString(): String = "%04d.%02d.%02d".format(year, month, day)

    companion object {
        fun from(date: String): MovieDateUiModel {
            val (year, month, day) = date.split(".").map { it.toInt() }
            return MovieDateUiModel(year, month, day)
        }
    }
}
