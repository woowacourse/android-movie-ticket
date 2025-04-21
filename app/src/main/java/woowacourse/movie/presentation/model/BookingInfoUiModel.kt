package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.MovieTime
import java.time.LocalDate

@Parcelize
data class BookingInfoUiModel(
    val movie: MovieUiModel = MovieUiModel(),
    val date: LocalDate = LocalDate.now(),
    val movieTime: MovieTime = MovieTime(),
    val ticketCount: Int = 0,
    val eachPrice: Int = 0,
) : Parcelable
