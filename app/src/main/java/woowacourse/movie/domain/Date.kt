package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
class Date(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Parcelable
