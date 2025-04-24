package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: String,
    val count: Int,
) : Parcelable
