package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    @DrawableRes val imageSource: Int,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
) : Parcelable {
    init {
        require(title.isNotBlank()) { "영화 제목은 비어있을 수 없다" }
        require(runningTime > 0) { "영화 상영시간은 0보다 커야한다" }
        require(isDateValid()) { "시작 날짜는 종료 날짜보다 앞서야합니다 " }
    }

    private fun isDateValid(): Boolean {
        return screeningStartDate.isBefore(screeningEndDate) ||
            screeningStartDate.isEqual(
                screeningEndDate,
            )
    }
}
