package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket(
    val title: String,
    val date: String,
    val time: String,
    val count: Int,
    val money: Int,
) : Parcelable
