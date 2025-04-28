package woowacourse.movie.uiModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket(
    val title: String,
    val date: String,
    val time: String,
    val seats: List<String> = listOf(),
    val count: Int,
    val money: Int,
) : Parcelable
