package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class BookingInfoUiModel(
    val movie: MovieUiModel = MovieUiModel(),
    val date: LocalDate = LocalDate.now(),
    val movieTime: MovieTimeUiModel = MovieTimeUiModel(),
    val ticketCount: Int = 0,
    val eachPrice: Int = 0,
    val totalPrice: Int = 0,
) : Parcelable
