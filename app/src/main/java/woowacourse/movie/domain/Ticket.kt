package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class Ticket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val count: Int = 0,
    val money: Int = 0,
    val seat: List<String> = emptyList(),
) : Parcelable
