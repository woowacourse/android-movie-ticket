package woowacourse.movie.domain.datetime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class ScreeningDateTime(val value: LocalDateTime, private val screeningPeriod: ScreeningPeriod) :
    Parcelable {
    init {
        validateDateTime()
    }

    private fun validateDateTime() {
        require(value.toLocalDate() in screeningPeriod.start..screeningPeriod.end) { SELECTED_SCREENING_DATE_TIME_ERROR }
    }

    companion object {
        private const val SELECTED_SCREENING_DATE_TIME_ERROR = "선택된 날짜가 영화 상영 기간 범위에서 벗어났습니다."
    }
}
