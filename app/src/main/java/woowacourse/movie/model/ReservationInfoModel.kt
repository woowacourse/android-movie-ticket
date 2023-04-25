package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationInfoModel(
    val title: String,
    val playingDate: String,
    val playingTime: String,
    val count: Int,
    val payment: String
) : Parcelable
