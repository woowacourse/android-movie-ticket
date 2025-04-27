package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.dto.MovieListData.MovieDto
import java.time.LocalDateTime

@Parcelize
data class ReservationDto(
    val movie: MovieDto,
    val memberCount: Int,
    val bookedTime: LocalDateTime,
) : Parcelable
