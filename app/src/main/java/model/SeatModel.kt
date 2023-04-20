package model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeatModel(
    val row: Int,
    val column: Int,
    val seatClass: String,
    val isReserved: Boolean,
) : Parcelable
