package woowacourse.movie.model.movie

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import woowacourse.movie.model.movie.MovieMapper.toDomainModel
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import java.time.LocalDate
import java.time.LocalDateTime

sealed class MovieData {
    abstract val viewType: Int

    companion object {
        const val CONTENT = 0
        const val ADVERTISEMENT = 1
    }
}

data class MovieAdvertisement(
    val link: String,
    @IdRes val image: Int,
) : MovieData() {
    override val viewType: Int = ADVERTISEMENT
}

data class MovieUiModel(
    val id: Long,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val screeningDates: List<LocalDate>,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int,
    @DrawableRes val poster: Int,
) : MovieData() {
    override val viewType: Int = CONTENT

    fun reserve(dateTime: LocalDateTime, seat: Seat): Ticket {
        return this.toDomainModel().reserve(dateTime, seat)
    }
}
