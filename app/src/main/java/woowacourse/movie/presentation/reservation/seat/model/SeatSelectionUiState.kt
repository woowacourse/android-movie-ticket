package woowacourse.movie.presentation.reservation.seat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs

@Parcelize
data class SeatSelectionUiState(
    val seatBoard: SeatBoardUiModel,
    val navArgs: SeatSelectionNavArgs,
) : Parcelable
