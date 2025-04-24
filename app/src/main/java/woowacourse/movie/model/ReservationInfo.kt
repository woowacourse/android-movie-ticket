package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationInfo(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String>,
    val price: Int,
) : Parcelable
