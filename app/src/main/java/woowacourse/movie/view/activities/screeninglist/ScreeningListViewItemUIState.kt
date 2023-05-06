package woowacourse.movie.view.activities.screeninglist

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate

sealed class ScreeningListViewItemUIState {
    data class ScreeningUIState(
        @DrawableRes val poster: Int,
        val title: String,
        val screeningStartDate: LocalDate,
        val screeningEndDate: LocalDate,
        val runningTime: Int,
        val screeningId: Long
    ) : ScreeningListViewItemUIState() {

        companion object {
            fun of(screening: Screening, @DrawableRes poster: Int): ScreeningUIState {
                val movie = screening.movie
                val screeningId = screening.id
                requireNotNull(screeningId) { "상영의 아이디가 널이면 UI 상태를 생성할 수 없습니다." }

                return ScreeningUIState(
                    poster,
                    movie.title,
                    screening.screeningRange.startDate,
                    screening.screeningRange.endDate,
                    movie.runningTime.value,
                    screeningId
                )
            }
        }
    }

    data class AdvertisementUIState(@DrawableRes val advertisementImage: Int) :
        ScreeningListViewItemUIState()
}
