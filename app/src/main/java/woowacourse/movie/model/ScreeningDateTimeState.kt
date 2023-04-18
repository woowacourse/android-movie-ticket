package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class ScreeningDateTimeState(
    val dateTime: LocalDateTime,
    val screeningPeriod: ScreeningPeriodState
) : Parcelable
