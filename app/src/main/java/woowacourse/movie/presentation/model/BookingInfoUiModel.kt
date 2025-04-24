package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingInfoUiModel(
    val movie: MovieUiModel = MovieUiModel(),
    val date: MovieDateUiModel = MovieDateUiModel(),
    val movieTime: MovieTimeUiModel = MovieTimeUiModel(),
    val ticketCount: Int = 0,
    val eachPrice: Int = 0,
    val totalPrice: Int = 0,
) : Parcelable
