package woowacourse.movie.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingInfoUiModel(
    val movie: MovieUiModel = MovieUiModel(),
    val date: MovieDateUiModel = MovieDateUiModel(),
    val movieTime: MovieTimeUiModel = MovieTimeUiModel(),
    val ticketCount: Int = 0,
    val totalPrice: Int = 0,
    val selectedSeats: Set<MovieSeatUiModel> = setOf<MovieSeatUiModel>(),
) : Parcelable
