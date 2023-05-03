package woowacourse.movie.view.activities.screeningdetail

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Screening
import java.time.LocalDate

data class ScreeningDetailUIState(
    @DrawableRes val poster: Int,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val summary: String,
    val screeningId: Long
) {

    companion object {
        fun of(screening: Screening, @DrawableRes poster: Int): ScreeningDetailUIState {
            val movie = screening.movie
            val screeningId = screening.id
            requireNotNull(screeningId) { "상영의 아이디가 널이면 UI 상태를 생성할 수 없습니다." }

            return ScreeningDetailUIState(
                poster,
                movie.title,
                screening.screeningRange.startDate,
                screening.screeningRange.endDate,
                movie.runningTime.value,
                movie.summary,
                screeningId
            )
        }
    }
}
