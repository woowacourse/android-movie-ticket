package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
class ScreeningPeriodState(
    val start: LocalDate,
    val end: LocalDate
) : Parcelable
