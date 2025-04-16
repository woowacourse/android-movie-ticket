package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ScreeningPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Parcelable
