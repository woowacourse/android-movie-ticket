package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ReservationInfo(
    val title: String,
    val date: LocalDate,
    val time: String,
    val seats: List<String>,
    val price: Int,
) : Parcelable
