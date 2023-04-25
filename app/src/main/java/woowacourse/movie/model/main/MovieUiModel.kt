package woowacourse.movie.model.main

import androidx.annotation.DrawableRes
import woowacourse.movie.ui.main.adapter.MainViewType
import java.time.LocalDate

data class MovieUiModel(
    override val id: Long,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val screeningDates: List<LocalDate>,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int,
    @DrawableRes val poster: Int,
) : MainData() {
    override val mainViewType: MainViewType = MainViewType.CONTENT
}
