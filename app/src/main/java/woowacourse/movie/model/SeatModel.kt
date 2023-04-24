package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatModel(val row: String, val column: String) : Parcelable
