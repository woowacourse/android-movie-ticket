package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ticket.SeatRank

@Parcelize
data class SeatUiModel(
    val rank: SeatRank,
    val position: PositionUiModel,
) : Parcelable
