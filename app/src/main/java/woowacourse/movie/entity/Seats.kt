package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class Seats(val value: List<Seat>) : Parcelable
