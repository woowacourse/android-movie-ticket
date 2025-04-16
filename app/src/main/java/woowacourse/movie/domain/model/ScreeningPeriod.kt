package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ScreeningPeriod(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) : Parcelable
