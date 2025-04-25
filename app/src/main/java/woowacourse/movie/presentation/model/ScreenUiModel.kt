package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.cinema.Screen

@Parcelize
class ScreenUiModel(
    val seats: List<SeatUiModel>,
) : Parcelable

fun Screen.toUiModel(): ScreenUiModel =
    ScreenUiModel(
        seats.map { it.toUiModel() },
    )
