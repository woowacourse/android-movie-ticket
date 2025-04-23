package woowacourse.movie.ui.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    val id: Long,
    val poster: Poster,
    val title: String,
    val screeningStartDate: String,
    val screeningEndDate: String,
    val runningTime: String,
) : Parcelable
