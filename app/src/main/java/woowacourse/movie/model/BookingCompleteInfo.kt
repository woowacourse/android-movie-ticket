package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingCompleteInfo(
    val movieBookingInfo: MovieBookingInfo,
    val totalPrice: Int,
    val seats: String
) : Parcelable
