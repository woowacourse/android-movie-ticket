package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class MovieTimeUiModel(
    val value: String = "",
) : Parcelable
