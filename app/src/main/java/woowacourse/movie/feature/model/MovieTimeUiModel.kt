package woowacourse.movie.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTimeUiModel(
    val hour: Int = 0,
    val minute: Int = 0,
) : Parcelable {
    override fun toString(): String = "%02d:%02d".format(hour, minute)

    companion object {
        fun from(time: String): MovieTimeUiModel =
            time.split(":").let {
                MovieTimeUiModel(
                    hour = it[0].toInt(),
                    minute = it[1].toInt(),
                )
            }
    }
}
