package woowacourse.movie.model.main

import androidx.annotation.DrawableRes
import woowacourse.movie.model.main.MovieMapper.toDomainModel
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import woowacourse.movie.ui.main.adapter.MainViewType
import java.time.LocalDate
import java.time.LocalDateTime

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

    fun reserve(dateTime: LocalDateTime, seat: Seat): Ticket {
        return this.toDomainModel().reserve(dateTime, seat)
    }
}
