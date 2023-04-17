package woowacourse.movie.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.model.Movie

@Parcelize
data class MovieUIModel(
    @DrawableRes val posterImage: Int = R.drawable.harrypotter_poster,
    val title: String,
    val screeningDay: ScreeningPeriod,
    val runningTime: Int,
    val description: String = ""
) : Parcelable {
    companion object {
        fun Movie.movieToMovieUiModel(): MovieUIModel =
            MovieUIModel(
                title = this.title,
                screeningDay = this.screeningDay,
                runningTime = this.runningTime,
                description = this.description
            )
    }
}
