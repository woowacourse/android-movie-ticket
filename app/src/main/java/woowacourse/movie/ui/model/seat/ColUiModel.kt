package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ColUiModel(
    val col: String,
) : Parcelable
