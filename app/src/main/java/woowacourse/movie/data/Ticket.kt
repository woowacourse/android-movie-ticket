package woowacourse.movie.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket (
    val title: String,
    val date: String,
    val time: String,
    val count : String,
    val money: String
): Parcelable