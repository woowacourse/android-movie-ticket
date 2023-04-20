package woowacourse.movie.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class RowUI(
    val x: Int
) : Parcelable
