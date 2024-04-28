package woowacourse.movie.moviereservation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
data class BookingInfoUiModel(
    val movieId: Long,
    val count: HeadCountUiModel,
    val date: SelectedDateUiModel,
    val time: SelectedTimeUiModel,
) : Parcelable {
    constructor(movieId: Long, count: Int, date: LocalDate, time: LocalTime) : this(
        movieId,
        HeadCountUiModel(count.toString()),
        SelectedDateUiModel(date = date),
        SelectedTimeUiModel(time = time),
    )

    fun maxSelectSize() = count.count.toInt()

    fun localDateTime() = LocalDateTime.of(date.getLocalDate(), time.getLocalTime())

    fun updateCount(updatedCount: HeadCountUiModel): BookingInfoUiModel = BookingInfoUiModel(movieId, updatedCount, date, time)

    fun updateCount(updatedCount: Int): BookingInfoUiModel = BookingInfoUiModel(movieId, HeadCountUiModel(updatedCount), date, time)

    fun updateDate(updatedDate: SelectedDateUiModel): BookingInfoUiModel = BookingInfoUiModel(movieId, count, updatedDate, time)

    fun updateDate(
        position: Int,
        updatedDate: String,
    ): BookingInfoUiModel = BookingInfoUiModel(movieId, count, SelectedDateUiModel(position, updatedDate), time)

    fun updateTime(updatedTime: SelectedTimeUiModel): BookingInfoUiModel = BookingInfoUiModel(movieId, count, date, updatedTime)

    fun updateTime(
        position: Int,
        updatedTime: String,
    ): BookingInfoUiModel = BookingInfoUiModel(movieId, count, date, SelectedTimeUiModel(position, updatedTime))
}
