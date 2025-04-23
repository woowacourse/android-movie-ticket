package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Ticket(
    val title: String,
    val date: LocalDateTime,
    val personnel: Int,
) : Parcelable
